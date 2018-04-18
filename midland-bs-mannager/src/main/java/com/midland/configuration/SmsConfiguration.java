package com.midland.configuration;

import com.github.qcloudsms.SmsSingleSender;
import com.midland.web.api.SmsSender.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
@Component
public class SmsConfiguration {

    @Autowired
    private SmsProperties smsProperties;

    @Bean
    public SmsSingleSender sender() {
        SmsSingleSender sender = null;
        try {
            sender = new SmsSingleSender(Integer.valueOf(smsProperties.getAppId()), smsProperties.getAppKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sender;
    }

}
