package com.midland.web.annocontroller;

import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.midland.core.entity.ThirdPartyUser;
import com.midland.core.util.ApplicationUtils;
import com.midland.core.util.MD5Util;
import com.midland.core.util.SmsUtil;
import com.midland.core.util.ThirdPartyLoginHelper;
import com.midland.web.api.SmsSender.SmsModel;
import com.midland.web.commons.FastJsonUtils;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ConstantUtils;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.WebUser;
import com.midland.web.model.user.User;
import com.midland.web.service.WebUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/anno")
public class annoUserController {
    @Resource
    private WebUserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SmsSingleSender sender;

    /**
     * 发送短信，短信登录验证
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/smsLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public String smsLogin(HttpServletRequest request, @RequestBody Map<String, String> parem) {
        Result<WebUser> result = new Result<>();
        String phone = parem.get("phone");
        String vCode = parem.get("vcode");
        WebUser userInfo = new WebUser();
        userInfo.setPhone(phone);
        userInfo = userService.findtUserByEntity(userInfo);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("flag", 0);
        String key = "midland:vcode:" + phone;
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        String redisVcode = null;
        if (vo.get(key) != null) {
            redisVcode = vo.get(key).toString();
        }
        if (redisVcode != null && redisVcode.equals(vCode)) {
            if (userInfo == null) {
                WebUser newUser = new WebUser();
                newUser.setPhone(phone);
                newUser.setUsername(MD5Util.MD5Encode(phone, "UTF-8"));
                Integer id = userService.addWebUser(newUser);
                newUser.setId(id);
                result.setModel(newUser);
            } else {
                result.setModel(userInfo);
            }
            map.put("flag", 1);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg(Result.resultMsg.SUCCESS.toString());
            String token = request.getSession().getId();
            request.getSession().setAttribute(ConstantUtils.USER_SESSION, userInfo);
            result.setToken(token);
            result.setMap(map);
            return JSONObject.toJSONString(result);
        } else {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("验证码错误！");
            return JSONObject.toJSONString(result);
        }
    }


    /**
     * 发送短信，短信登录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/vcode/sendSms")
    public String vcodeSendSms(@RequestBody Map<String, String> parem) {
        Result<WebUser> result1 = new Result<>();
        String vcode = SmsUtil.createRandomVCode();//验证码
        String key = "midland:vcode:" + parem.get("phone");
        List list = new ArrayList();
        list.add(vcode);
        list.add("5");
        SmsModel smsModel = new SmsModel(parem.get("phone"), "54711", list);
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key, vcode);
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);//15分钟过期
        try {
            SmsSingleSenderResult result = sender.sendWithParam("86", smsModel.getPhones(), Integer.valueOf(smsModel.getTpId()), (ArrayList<String>) smsModel.getFieldsList(), "", "", "");

            if (result.errMsg.equals("OK")) {
                result1.setMsg(Result.resultMsg.SUCCESS.toString());
                result1.setCode(ResultStatusUtils.STATUS_CODE_200);
            } else {
                result1.setMsg("发送失败！");
                result1.setCode(ResultStatusUtils.STATUS_CODE_203);
            }
        } catch (Exception e) {
        }

        return JSONObject.toJSONString(result1);
    }


    /**
     * 发送短信,忘记密码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/forget/sendSms")
    public String forgetSendSms(@RequestParam Map<String, String> parem) {
        WebUser user = new WebUser();
        user.setPhone(parem.get("phone"));
        Map<Object, Object> map = new HashMap<Object, Object>();
        Result<WebUser> result_ = new Result<>();
        map.put("flag", 0);
        if (StringUtils.isEmpty(parem.get("phone"))) {
            map.put("msg", "手机号不能为空！");
            result_.setMsg("手机号不能为空");
            result_.setMap(map);
            result_.setCode(ResultStatusUtils.STATUS_CODE_203);
            return FastJsonUtils.toJSONStr(result_);
        }
        String vcode = SmsUtil.createRandomVCode();//验证码
        String mobile = "";
        String key = "midland:vcode:" + parem.get("phone");
        user = userService.findtUserByEntity(user);
        if (user != null) {
            mobile = user.getPhone();
            if (mobile != null && mobile.length() > 0) {
                List list = new ArrayList();
                list.add(vcode);
                list.add("5");
                SmsModel smsModel = new SmsModel(mobile, "54711", list);
                ValueOperations<String, Object> vo = redisTemplate.opsForValue();
                vo.set(key, vcode);
                redisTemplate.expire(key, 5, TimeUnit.MINUTES);//15分钟过期
                try {
                    SmsSingleSenderResult result = sender.sendWithParam("86", smsModel.getPhones(), Integer.valueOf(smsModel.getTpId()), (ArrayList<String>) smsModel.getFieldsList(), "", "", "");

                    if (result.errMsg.equals("OK")) {
                        map.put("flag", 1);
                        map.put("id", user.getId());
                        map.put("msg", "发送成功!");
                        result_.setMap(map);
                        result_.setMsg(Result.resultMsg.SUCCESS.toString());
                        result_.setCode(ResultStatusUtils.STATUS_CODE_200);
                        return FastJsonUtils.toJSONStr(result_);
                    } else {
                        map.put("id", user.getId());
                        map.put("msg", "短信发送失败，请稍后再试!");
                        result_.setMsg("短信发送失败，请稍后再试!");
                        result_.setMap(map);
                        result_.setCode(ResultStatusUtils.STATUS_CODE_203);
                        return FastJsonUtils.toJSONStr(result_);
                    }
                } catch (Exception e) {
                    map.put("id", user.getId());
                    result_.setMsg("短信发送失败，请稍后再试!");
                    result_.setMap(map);
                    result_.setCode(ResultStatusUtils.STATUS_CODE_203);
                    return FastJsonUtils.toJSONStr(result_);
                }

            } else {
                map.put("msg", "该用户名未绑定有效的手机号码!");
                result_.setMsg("该用户名未绑定有效的手机号码!");
                result_.setMap(map);
                result_.setCode(ResultStatusUtils.STATUS_CODE_203);
                return FastJsonUtils.toJSONStr(result_);
            }
        } else {
            map.put("msg", "无效的手机号!");
            result_.setMsg("无效的手机号!");
            result_.setMap(map);
            result_.setCode(ResultStatusUtils.STATUS_CODE_203);
        }
        return FastJsonUtils.toJSONStr(result_);
    }


    /**
     * 验证码校验,忘记密码
     *
     * @param vcode
     * @return
     */
    @RequestMapping(value = "/checkVcode", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkVcode_(String phone, String vcode) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Result<WebUser> result_ = new Result<>();
        map.put("flag", 0);
        String key = "midland:vcode:" + phone;
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        String redisVcode = vo.get(key).toString();
        if (redisVcode.equals(vcode)) {
            map.put("flag", 1);
            result_.setMap(map);
            result_.setMsg(Result.resultMsg.SUCCESS.toString());
            result_.setCode(ResultStatusUtils.STATUS_CODE_200);

        }
        return FastJsonUtils.toJSONStr(result_);
    }


    /**
     * 修改密码提交保存,忘记密码
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = {RequestMethod.GET, RequestMethod.POST})
    public String updatePwd_(HttpServletRequest request, String newPwd, String id) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Result<WebUser> result_ = new Result<>();
        map.put("flag", 0);
        WebUser user = new WebUser();
        user.setPassword(ApplicationUtils.sha256Hex(newPwd));
        user.setId(Integer.valueOf(id));
        int n = userService.updateUser(user);
        if (n > 0) {
            map.put("flag", 1);
            result_.setMap(map);
            result_.setMsg(Result.resultMsg.SUCCESS.toString());
            result_.setCode(ResultStatusUtils.STATUS_CODE_200);
        }
        return FastJsonUtils.toJSONStr(result_);
    }


}
