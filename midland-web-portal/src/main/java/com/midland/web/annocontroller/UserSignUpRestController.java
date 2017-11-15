package com.midland.web.annocontroller;

import com.midland.core.util.SmsUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.controller.WebUserController;
import com.midland.web.service.WebUserService;
import com.midland.web.service.impl.PublicService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
    public void sign_up_SendSMS(@RequestBody Map map) {
        Result result = new Result();
        try {
            String phone = (String) map.get("phone");
            if (StringUtils.isEmpty(phone)) {
                throw new Exception("手机号码为空");
            }
            String vcode = SmsUtil.createRandomVcode();//验证码
            String key = Contant.SIGN_UP_VCODE_KEY + phone;
            publicServiceImpl.setV(key, vcode, 1, TimeUnit.MINUTES);
            List list = new ArrayList();
            list.add(vcode);
            list.add("1");
            apiHelper.smsSender(phone, 54711, list);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            logger.error("发送验证码失败", e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("发送验证码失败 ");
        }

    }

    /**
     * 校验验证码
     * @param map
     * @return
     */
    @RequestMapping("checkVcode")
    public Object checkVcode_(@RequestBody Map map) {
        Result result = new Result();
        try {
            String phone = (String) map.get("phone");
            String vcode = (String) map.get("vcode");
            String key = Contant.SIGN_UP_VCODE_KEY + phone;
            String redisVcode = (String) publicServiceImpl.getV(key);
            if (redisVcode.equals(vcode)) {
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("success");
            }else {
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                result.setMsg("error");
            }
        } catch (Exception e) {
            logger.error("checkVcode_", e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("error");
        }
        return result;
    }

    @RequestMapping("/sign_up")
    public Object sign_up(@RequestBody Map<String, String> param) {
        Result result = new Result();
        Result result1 = (Result)checkVcode_(param);
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
