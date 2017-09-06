package com.midland.configuration;

import com.midland.web.SmsSender.SmsClient;
import com.midland.web.SmsSender.SmsProperties;
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
		smsClient.setSessionId(smsProperties.getSessionId());
		smsClient.setType(Integer.valueOf(smsProperties.getType()));
		smsClient.setUserId(smsProperties.getUserId());
		smsClient.setWebKey(smsProperties.getWebKey());
		smsClient.setUrl(smsProperties.getUrl());
		smsClient.setCharset(smsProperties.getCharset());
		return smsClient;
	}
	
}
