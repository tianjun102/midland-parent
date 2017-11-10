package com.midland.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.midland.core.util.SmsUtil;
import com.midland.core.util.UploadImgUtil;
import com.midland.web.commons.core.util.ApplicationUtils;
import com.midland.web.commons.core.util.ConstantUtils;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.commons.exception.ServiceException;
import com.midland.web.model.WebUser;
import com.midland.web.service.WebUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Maps;
import com.midland.web.commons.FastJsonUtils;
import com.midland.web.commons.Result;

/**
 * @Title: UserController.java
 * @Package com.bluemobi.midland.web.controller
 * @Description: 用户表示层类
 * @author yek
 * @date 2017年3月22日 上午8:53:59
 */
@RestController
@RequestMapping(value = "/user")
public class WebUserController extends WebCommonsController {

	private static Logger logger = Logger.getLogger(WebUserController.class);

	@Resource
	private WebUserService userService;


    @Resource
	private RedisTemplate<String, Object> redisTemplate;
    

	/**
	 * 用户登录
	 * 
	 * @param remember
	 *            是否记住密码
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String login(@RequestBody WebUser user, boolean remember, HttpServletRequest request,
                        HttpServletResponse response) {
		Result<WebUser> result = new Result<>();
		// 登录日志状态：0,正常;1,错误
		Short isError = 0;
		// 获取当前登录用户IP
		String loginIP = request.getRemoteAddr();

		try {
			// 判断用户名和密码是否为空值
			if (StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {

				WebUser userInfo = userService.getUserByUsername(user.getUsername());

				if (null != userInfo) {

					// 获取用户输入密码进行加密处理
					String encryptedPwd = ApplicationUtils.sha256Hex(user.getPassword());

					if (userInfo.getPassword().equals(encryptedPwd)) {

						// 用户登录验证成功后保存用户信息到SESSION
						
						/*if(userInfo.getId()!=null){
							userInfo.setMsgNum(noticeService.countNoticeNum(userInfo.getId()));
						}*/
						
						request.getSession().setAttribute(ConstantUtils.USER_SESSION, userInfo);
						if (userInfo.getUserType() == 2) {
							WebUser catUser = userService.findParentUserByChild(userInfo.getUsername());
							if(catUser!=null){
							userInfo.setParentId(catUser.getId());
							userInfo.setCustName(catUser.getCustName());
							}
						}
						String sessionId = request.getSession().getId();

						// "1"表示用户勾选记住密码
						if (remember) {

							// 创建Cookie
							Cookie usernameCookie = new Cookie(ConstantUtils.USER_INFO_COOKIE_USERNAME,
									user.getUsername());

							usernameCookie.setPath(request.getContextPath());
							// cookie保存用户信息时限
							usernameCookie.setMaxAge(99999999);
							response.addCookie(usernameCookie);

							// 创建Cookie
							Cookie encryptedPwdCookie = new Cookie(ConstantUtils.USER_INFO_COOKIE_PASSWORD,
									encryptedPwd);

							encryptedPwdCookie.setPath(request.getContextPath());
							// cookie保存用户信息时限
							encryptedPwdCookie.setMaxAge(99999999);
							response.addCookie(encryptedPwdCookie);
						}
						result.setToken(sessionId);
						result.setCode(ResultStatusUtils.STATUS_CODE_200);
						result.setMsg(Result.resultMsg.SUCCESS.toString());
						result.setModel(userInfo);
					} else {
						isError = 1;
						result.setCode(ResultStatusUtils.STATUS_CODE_203);
						result.setMsg("输入密码不正确。");
						logger.warn("输入密码不正确。");
					}
				} else {
					isError = 1;
					result.setCode(ResultStatusUtils.STATUS_CODE_203);
					result.setMsg("用户不存在。");
					logger.warn("用户不存在。");
				}

			}
		} catch (ServiceException e) {
			isError = 1;
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("用户名密码错误！");
			logger.error("用户登录时出现异常。", e);
		}

		// 添加登录日志
		/*sysLogService.addSysLog(
				new SysLog(Short.valueOf("0"), user.getUsername() + "用户登录", loginIP, user.getUsername(), isError));*/
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Result<WebUser> result = new Result<>();
		HttpSession session = request.getSession();
		/** 删除session用户信息 */
		session.removeAttribute(ConstantUtils.USER_SESSION);
		session.invalidate();

		/** 删除cookie用户信息 */
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (ConstantUtils.USER_INFO_COOKIE_USERNAME.equals(cookie.getName())) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					break;
				}
				if (ConstantUtils.USER_INFO_COOKIE_PASSWORD.equals(cookie.getName())) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					break;
				}
			}
		}
		result.setCode(ResultStatusUtils.STATUS_CODE_200);
		result.setMsg(Result.resultMsg.SUCCESS.toString());
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/editPwd", method = { RequestMethod.POST },produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String editPwd(@RequestBody Map<String, String> params, HttpServletRequest request) {
		Result<WebUser> result = new Result<>();
		if (params == null ||params.isEmpty()) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("请求参数为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("oldPassWord"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("原始密码为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("passWord"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("新密码为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		WebUser user = (WebUser) request.getSession().getAttribute(ConstantUtils.USER_SESSION);
		if (null == user) {
			result.setCode(ResultStatusUtils.STATUS_CODE_303);
			result.setMsg("用户已退出，请重新登录！");
			return FastJsonUtils.toJSONStr(result);
		}
		if(!ApplicationUtils.sha256Hex(params.get("oldPassWord")).equals(user.getPassword())){
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("原始密码不正确!");
			return FastJsonUtils.toJSONStr(result);
		}
		
		
		try {
				int i = userService.editPwdById(ApplicationUtils.sha256Hex(params.get("passWord")), user.getId());
				if (i>0) {
					result.setCode(ResultStatusUtils.STATUS_CODE_200);
					result.setMsg("修改密码成功!");
				}else{
					result.setCode(ResultStatusUtils.STATUS_CODE_200);
					result.setMsg("请求成功,密码尚未被修改!");
				}
		} catch (ServiceException e) {
			logger.error("修改密码时出现异常。", e);
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("修改密码失败!");
		}
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 跳到个人信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String toEdit(HttpServletRequest request, @RequestBody Map<String,String> params) {
		Result<WebUser> result = new Result<>();
		WebUser user = userService.getUserByUserId(Integer.valueOf(params.get("userId")));

		result.setCode(ResultStatusUtils.STATUS_CODE_200);
		result.setMsg(Result.resultMsg.SUCCESS.toString());
		result.setModel(user);

		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 修改个人信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String editInformtion(@RequestBody Map<String, String> params, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Result<WebUser> result = new Result<>();
		try {
			Object obj = session.getAttribute(ConstantUtils.USER_SESSION);
			if (null != obj) {

				WebUser user = (WebUser) obj;

				user.setPhone(params.get("phone"));
				user.setEmail(params.get("email"));

				int i = userService.editUserById(user);
				result.setNumber(i);
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg(Result.resultMsg.SUCCESS.toString());
			}
		} catch (ServiceException e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("修改个人信息时出现异常。");
			logger.error("修改个人信息时出现异常。", e);
		}
		return FastJsonUtils.toJSONStr(result);
	}

	
	@RequestMapping(value = "/changeHeadImg", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String changeHeadImg(@RequestBody Map<String, String> params,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Result<WebUser> result = new Result<>();
		try {
			if (params == null ||params.isEmpty()) {
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("请求参数为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			if(StringUtils.isEmpty(params.get("imgContent"))){
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("图片内容为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			
			Object obj = session.getAttribute(ConstantUtils.USER_SESSION);
			if (null != obj) {
				WebUser user = (WebUser) obj;
				Map<String, String> map = Maps.newHashMap();
				map.put("path", "/home/upload/");
				map.put("userName", user.getUsername());
				map.put("oldImg",user.getHeadImg());
				map.put("imgContent",params.get("imgContent"));
				String headImg  = UploadImgUtil.GenerateImage(map);

				if(StringUtils.isNotEmpty(headImg)){
					user.setHeadImg(headImg);
				}
				int i = userService.editUserById(user);
				result.setNumber(i);
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg(Result.resultMsg.SUCCESS.toString());
			}
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("修改头像出错,文件不正确。");
			logger.error("修改头像出错,文件不正确", e);
		}
		return FastJsonUtils.toJSONStr(result);
	}
	/**
	 * 检查用户名
	 * 
	 *            用户名
	 * @return
	 */
	@RequestMapping(value = "/checkPhone", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String checkPhone(@RequestBody Map<String, String> params) {
		Result<WebUser> result = new Result<>();
		if (params == null ||params.isEmpty()) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("请求参数为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("phone"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("手机号码为空!");
			return FastJsonUtils.toJSONStr(result);
		}

		try {
			int i = userService.authentication(params.get("phone"));
			result.setNumber(i);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("校验手机号成功!");
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("校验手机号失败!");
			return FastJsonUtils.toJSONStr(result);
		}
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * 新增安装员
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addInstaller",method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String addInstaller(@RequestBody Map<String, String> params,HttpServletRequest request) {
		Result<WebUser> result = new Result<>();
		if (params == null ||params.isEmpty()) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("请求参数为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("userName"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("用户姓名为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("phone"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("手机电话为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		WebUser user = (WebUser) request.getSession().getAttribute(ConstantUtils.USER_SESSION);
		if (null == user) {
			result.setCode(ResultStatusUtils.STATUS_CODE_303);
			result.setMsg("用户已退出，请重新登录！");
			return FastJsonUtils.toJSONStr(result);
		}else{
			params.put("userCnName",user.getUsername());
			params.put("createBy",user.getId().toString());
		}
		try {
			if(userService.addUser(params)>0){
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg("新增用户成功!");
			}else{
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg("调用成功,但用户未被新增!");
			}
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("新增用户失败!");
			return FastJsonUtils.toJSONStr(result);
		}
		
		return FastJsonUtils.toJSONStr(result);
	}

	
	//获取所有安装员
	@RequestMapping(value = "/installerList", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String installerList(@RequestBody Map<String, String> params,HttpServletRequest request) {
		Result<WebUser> result = new Result<>();
		if (params == null ||params.isEmpty()) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("请求参数为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (params.get("username") == null) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("姓名为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (params.get("phone") == null) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("手机号为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		WebUser user = (WebUser) request.getSession().getAttribute(ConstantUtils.USER_SESSION);
		if (null == user) {
			result.setCode(ResultStatusUtils.STATUS_CODE_303);
			result.setMsg("用户已退出，请重新登录！");
			return FastJsonUtils.toJSONStr(result);
		}else{
			params.put("createBy",user.getId().toString());
		}
		try {
			PageList<WebUser>  items = userService.findBycreateByPage(params, new PageBounds());
			Paginator paginator = items.getPaginator();
			Map<Object, Object> map = new HashMap<>();
			map.put("paginator", paginator);
			map.put("items", items);
			result.setMap(map);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("查询安装员列表成功!");
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("查询安装员列表失败!");
			return FastJsonUtils.toJSONStr(result);
		}
		return FastJsonUtils.toJSONStr(result);
	}
	
	
	//更改安装员信息  (删除)
	@RequestMapping(value = "/changeInstaller",method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String changeInstaller(@RequestBody Map<String, String> params) {
		Result<WebUser> result = new Result<>();
		if (params == null ||params.isEmpty()) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("请求参数为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("type"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("操作类型为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (params.get("id")==null) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("用户id为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		
		WebUser user = new WebUser();
		user.setId(Integer.valueOf(params.get("id")));
		if("delete".equals(params.get("type"))){
			user.setState(3);
		}else{
			if (params.get("userName")==null) {
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("用户姓名为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			if (params.get("phone")==null) {
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("手机电话为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			user.setUserCnName(params.get("userName"));
			user.setPhone(params.get("phone"));
		}
		
		try {
			if(userService.modifyUser(user)>0){
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg("修改用户成功!");
			}else{
				result.setCode(ResultStatusUtils.STATUS_CODE_200);
				result.setMsg("修改成功,但用户未被新增!");
			}
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("新增用户失败!");
			return FastJsonUtils.toJSONStr(result);
		}
		
		return FastJsonUtils.toJSONStr(result);
	}
	
	
	//查询安装员明细
	@RequestMapping(value = "/installerDetail",method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String installerDetail(@RequestBody Map<String, String> params) {
		Result<WebUser> result = new Result<>();
		if (params == null ||params.isEmpty()) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("请求参数为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		if (StringUtils.isEmpty(params.get("id"))) {
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			result.setMsg("用户姓名为空!");
			return FastJsonUtils.toJSONStr(result);
		}
		try {
			WebUser user =  userService.queryUser(Integer.valueOf(params.get("id")));
			result.setModel(user);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("查询用户明细成功!");
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("查询用户明细失败!");
			return FastJsonUtils.toJSONStr(result);
		}
		
		return FastJsonUtils.toJSONStr(result);
	}
	
	
    /**
     * 发送短信
     * @param parems
     * @return
     */
    @RequestMapping(value = "/sendSms",produces = "application/json; charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST})
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
    	String mobile=parems.get("phone").toString();
    	String key="wks:vcode:"+parems.get("phone");
    	String content="";
		/*User parem = new User();
		parem.setPhone(parems.get("phone").toString());*/
		/*User user= userService.findtUserByEntity(parem);*/
    	/*if(user!=null){*/
    		/*mobile=user.getPhone();*/
    		content="您正在找回密码,验证码:"+vcode+",请在15分钟内按页面提示提交验证码,切勿将验证码泄露于他人。";
    		/*if(mobile!=null && mobile.length()>0){*/
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
        			result.setCode(ResultStatusUtils.STATUS_CODE_200);
        			result.setMsg(Result.resultMsg.SUCCESS.toString());
        			result.setMap(map);
            	}
        	/*}else{
        		map.put("msg", "该用户名未绑定有效的手机号码!");
    			result.setCode(ResultStatusUtils.STATUS_CODE_200);
    			result.setMsg(Result.resultMsg.SUCCESS.toString());
    			result.setMap(map);
        	}*/
    	/*}else{
    		map.put("msg", "无效的手机号码!");
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg(Result.resultMsg.SUCCESS.toString());
			result.setMap(map);
    	}*/
    	return JSONObject.toJSONString(result);
    }
    
    /**
     * 验证码校验
     * @param parem
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
			result.setMap(map);
    	}
    	return JSONObject.toJSONString(result);
    }



    

	
}
