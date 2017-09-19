package com.midland.web.SmsSender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
@Component
public class SmsProperties {
	
	@Value("${url}")
	private String url;
	/**
	 * 应用id
	 */
	@Value("${bizAppId}")
	private String bizAppId;
	/**
	 * 客户编号
	 */
	@Value("${customerId}")
	private String customerId;
	
	/**
	 * 	用户名编号
	 */
	@Value("${userId}")
	private String userId;


	@Value("${charset}")
	private String charset ="utf-8";

	@Value("${password}")
	private String password;


	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getBizAppId() {
		return bizAppId;
	}
	
	public void setBizAppId(String bizAppId) {
		this.bizAppId = bizAppId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCharset() {
		return charset;
	}
	
	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
