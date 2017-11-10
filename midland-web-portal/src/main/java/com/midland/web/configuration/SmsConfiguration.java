package com.midland.web.configuration;

import com.github.qcloudsms.SmsSingleSender;
import com.midland.web.api.SmsSender.SmsClient;
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
	public SmsClient smsClient(){
		SmsClient smsClient = new SmsClient();
		
		smsClient.setBizAppId(smsProperties.getBizAppId());
		smsClient.setCustomerId(smsProperties.getCustomerId());
		smsClient.setUserId(smsProperties.getUserId());
		smsClient.setUrl(smsProperties.getUrl());
		smsClient.setCharset(smsProperties.getCharset());
		smsClient.setPassword(smsProperties.getPassword());
		return smsClient;
	}

	@Bean
	public SmsSingleSender sender(){
		SmsSingleSender sender = null;
		try {
			sender = new SmsSingleSender(1400048150, "cf34fd4134eb80f7accac8a37329a873");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sender;
	}
	
}
