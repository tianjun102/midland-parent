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
	/**
	 * 接口通信唯一标识，64位16进制数，每次登录重新生成,如果非用户操作，可以为空。
	 */
	@Value("${webKey}")
	private String webKey;
	/**
	 * 	会话标识，非用户操作时，必填
	 */
	@Value("${sessionId}")
	private String sessionId;
	
	/**
	 * 	产品的类别
	 */
	@Value("${type}")
	private String type;
	
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
	
	public String getWebKey() {
		return webKey;
	}
	
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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
