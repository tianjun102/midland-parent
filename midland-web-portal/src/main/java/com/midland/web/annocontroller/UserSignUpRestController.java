package com.midland.web.annocontroller;

import com.midland.core.util.SmsUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.controller.WebUserController;
import com.midland.web.service.WebUserService;
import com.midland.web.service.impl.PublicService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sys")
public class UserSignUpRestController {

    private static Logger logger = Logger.getLogger(WebUserController.class);

    @Resource
    private WebUserService webUserService;

    @Resource
    private ApiHelper apiHelper;
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
        int tpId=54711;
        return publicServiceImpl.sendCode(phone,tpId,vCode,key,codeEffective,TimeUnit.MINUTES);
    }
    /**
     * 校验验证码
     * @param map
     * @return
     */
    @RequestMapping("checkVCode")
    public Object checkVCode_(@RequestBody Map map) {
        String phone = (String) map.get("phone");
        String vCode = (String) map.get("vcode");
        String key = Contant.SIGN_UP_VCODE_KEY + phone;
        return publicServiceImpl.codeCheck(phone,vCode,key);
    }


    @RequestMapping("/sign_up")
    public Object sign_up(@RequestBody Map<String, String> param) {
        Result result = new Result();
        Result result1 = (Result)checkVCode_(param);
        if (ResultStatusUtils.STATUS_CODE_200 == result1.getCode()) {
            try {
                String password = param.get("password");
                String confirmPassword = param.get("confirmPassword");
                if (password != null && password.equals(confirmPassword)) {
                    webUserService.addUser(param);
                    result.setCode(ResultStatusUtils.STATUS_CODE_200);
                    result.setMsg("sign up success");
                } else {
                    result.setCode(ResultStatusUtils.STATUS_CODE_203);
                    result.setMsg("Password and Confirm Password inconsistent!");
                }
            } catch (Exception e) {
                logger.error("sign_up:", e);
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                result.setMsg("sign up success error");
            }
        }
        result.setCode(ResultStatusUtils.STATUS_CODE_203);
        result.setMsg("vcode is incorrect");
        return result;
    }


}
