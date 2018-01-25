package com.midland.web.api;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'ms.x' on 2017/9/19.
 */
@Component
public class ApiHelper {

    @Autowired
    private JavaMailSender javaMailSender;
    private final Logger logger = LoggerFactory.getLogger(ApiHelper.class);
    @Autowired
    private SmsSingleSender sender;

    public void smsSender(String phone, int tpId, List<String> paramList) {
        try {
            if (StringUtils.isEmpty(phone)) {
                throw new IllegalArgumentException("phone is null");
            }
            SmsSingleSenderResult result = sender.sendWithParam(null,
                    phone, tpId, (ArrayList<String>) paramList, "", "", "");
            if (result.errMsg.equals("OK")) {
                logger.info("smsSender  发送短信成功，{}，{}，{}", phone, tpId, paramList);
            } else {
                logger.error("smsSender  发送短信失败，{}，{}，{}，{}", phone, tpId, paramList, result);
            }
        } catch (Exception e) {
            logger.error("smsSender  发送短信失败，{}，{}，{}", phone, tpId, paramList, e);
        }
    }

    public void emailSender(String methodName, SimpleMailMessage message) {
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            logger.error(methodName + " 发送邮件失败", e);
        }
    }


}
