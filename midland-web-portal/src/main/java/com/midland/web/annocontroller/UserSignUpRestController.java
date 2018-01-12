package com.midland.web.annocontroller;

import com.midland.core.util.SmsUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.controller.WebUserController;
import com.midland.web.service.WebUserService;
import com.midland.web.service.impl.PublicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sys")
public class UserSignUpRestController {

    private static Logger logger = LoggerFactory.getLogger(WebUserController.class);

    @Resource
    private WebUserService webUserService;
    @Resource
    private PublicService publicServiceImpl;


    /**
     * 注册发送验证码
     * @param map
     */
    @RequestMapping("send/code")
    public Object sign_up_SendSMS(@RequestBody Map map) {
        String phone = (String) map.get("phone");
        String key = Contant.SIGN_UP_VCODE_KEY + phone;
        int codeEffective=3;
        String vCode = SmsUtil.createRandomVCode();//验证码
        return publicServiceImpl.sendCode(phone,Contant.SMS_TEMPLATE_54711,vCode,key,codeEffective,TimeUnit.MINUTES);
    }
    /**
     * 校验注册验证码
     * @param map
     * @return
     */
    @RequestMapping("checkVCode")
    public Object checkVCode_(@RequestBody Map map) {
        String phone = (String) map.get("phone");
        String vCode = (String) map.get("vCode");
        String key = Contant.SIGN_UP_VCODE_KEY + phone;
        return publicServiceImpl.codeCheck(phone,vCode,key);
    }

    /**
     * 提交注册
     * @param param
     * @return
     */
    @RequestMapping("/sign_up")
    public Object sign_up(@RequestBody Map<String, String> param) {
        Result result = new Result();
        Result result1 = (Result)checkVCode_(param);
        if (ResultStatusUtils.STATUS_CODE_200 == result1.getCode()) {
            try {
                String phone = param.get("phone");
                String password = param.get("password");
                if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){//phone、password不能为空
                    result.setCode(ResultStatusUtils.STATUS_CODE_203);
                    result.setMsg("IllegalArgumentException");
                    return result;
                }
                String confirmPassword = param.get("confirmPassword");
                if (password != null && password.equals(confirmPassword)) {
                    webUserService.addUser(param);
                    result.setCode(ResultStatusUtils.STATUS_CODE_200);
                    result.setMsg("注册成功");
                    return result;
                } else {
                    result.setCode(ResultStatusUtils.STATUS_CODE_203);
                    result.setMsg("重复密码错误");
                    return result;
                }
            } catch (Exception e) {
                if (e instanceof DuplicateKeyException){
                    logger.error("codeCheck", e);
                    result.setCode(ResultStatusUtils.STATUS_CODE_203);
                    result.setMsg("用户已存在");
                    return result;
                }else {
                    logger.error("sign_up:", e);
                    result.setCode(ResultStatusUtils.STATUS_CODE_203);
                    result.setMsg("注册失败");
                    return result;
                }
            }
        }
        result.setCode(ResultStatusUtils.STATUS_CODE_203);
        result.setMsg("验证码错误");
        return result;
    }


}
