package com.midland.web.annocontroller;

import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.midland.core.util.SmsUtil;
import com.midland.web.commons.core.util.ApplicationUtils;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.controller.WebCommonsController;
import com.midland.web.model.WebUser;
import com.midland.web.service.WebUserService;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.midland.web.commons.Result;

/**
 * @Title: UserController.java
 * @Package com.bluemobi.midland.web.controller
 * @Description: 用户表示层类
 * @author yek
 * @date 2017年3月22日 上午8:53:59
 */
@RestController
@RequestMapping(value = "/updateuser")
public class CenterUserController extends WebCommonsController {

	private static Logger logger = Logger.getLogger(CenterUserController.class);

	@Resource
	private WebUserService webUserServiceImpl;


    @Resource
	private RedisTemplate<String, Object> redisTemplate;
	
    /**
     * 发送短信
     * @param
     * @return
     */
    @RequestMapping(value = "/sendSms",produces = "application/json; charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String sendSms(@RequestBody Map<String, Object> parems){
    	Result<WebUser> result = new Result<>();
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	map.put("flag", "false");
    	if(parems.get("phone")==null||parems.get("phone").equals("")){
        	map.put("msg", "手机号不能为空!");
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg(Result.resultMsg.SUCCESS.toString());
			return JSONObject.toJSONString(result);
    	}
    	
    	String vcode = SmsUtil.createRandomVcode();//验证码
    	String mobile="";
    	String key="wks:vcode:"+parems.get("phone");
    	String content="";
		WebUser parem = new WebUser();
		parem.setPhone(parems.get("phone").toString());
		WebUser user= webUserServiceImpl.findtUserByEntity(parem);
    	if(user!=null){
    		mobile=user.getPhone();
    		content="您正在找回密码,验证码:"+vcode+",请在15分钟内按页面提示提交验证码,切勿将验证码泄露于他人。";
    		if(mobile!=null && mobile.length()>0){
        		if(SmsUtil.send(mobile, content)){
            		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
                	vo.set(key, vcode);
                	redisTemplate.expire(key, 15,TimeUnit.MINUTES);//15分钟过期
                	map.put("flag", "true");
                	map.put("msg", "发送成功!");
        			result.setCode(ResultStatusUtils.STATUS_CODE_200);
        			result.setMsg(Result.resultMsg.SUCCESS.toString());
        			result.setMap(map);
            	}else{
            		map.put("msg", "短信发送失败，请稍后再试!");
        			result.setCode(ResultStatusUtils.STATUS_CODE_203);
        			result.setMsg(Result.resultMsg.SUCCESS.toString());
        			result.setMap(map);
            	}
        	}else{
        		map.put("msg", "该用户名未绑定有效的手机号码!");
    			result.setCode(ResultStatusUtils.STATUS_CODE_203);
    			result.setMsg(Result.resultMsg.SUCCESS.toString());
    			result.setMap(map);
        	}
    	}else{
    		map.put("msg", "无效的手机号码!");
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg(Result.resultMsg.SUCCESS.toString());
			result.setMap(map);
    	}
    	return JSONObject.toJSONString(result);
    }
    
    /**
     * 验证码校验
     * @param
     * @return
     */
    @RequestMapping(value = "/checkVcode", method = {RequestMethod.GET,RequestMethod.POST})
    public  String checkVcode(@RequestBody Map<String, String> parem){
    	Result<WebUser> result = new Result<>();
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	map.put("flag", "false");
    	String key="wks:vcode:"+parem.get("phone");
    	ValueOperations<String, Object> vo = redisTemplate.opsForValue();
    	String redisVcode=vo.get(key).toString();
    	if(redisVcode.equals(parem.get("vcode"))){
    		map.put("flag", "true");
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg(Result.resultMsg.SUCCESS.toString());
    	}else{
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg(Result.resultMsg.SUCCESS.toString());
			map.put("msg", "无效的验证码");
    	}
    	result.setMap(map);
    	return JSONObject.toJSONString(result);
    }
    
    /**
     * 修改密码
     */
    
    @RequestMapping(value = "/updatePwd", method = {RequestMethod.GET,RequestMethod.POST})
    public String updatePwd(HttpServletRequest request,@RequestBody Map<String, String> parem){
    	Result<WebUser> result = new Result<>();
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	map.put("flag", "false");
    	String newPwd=parem.get("newPwd");
    	String phone = parem.get("phone");
    	WebUser user=new WebUser();
    	user.setPhone(phone);
    	WebUser oldUser= webUserServiceImpl.findtUserByEntity(user);
    	oldUser.setPassword(ApplicationUtils.sha256Hex(newPwd));
    	int n= webUserServiceImpl.updateUser(oldUser);
        // 登出操作
        if(n>0){
    		map.put("flag", "true");
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg(Result.resultMsg.SUCCESS.toString());
			result.setMap(map);
        }
    	return JSONObject.toJSONString(result);
    }
    

}
