package com.midland.web.mailSender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 'ms.x' on 2017/9/19.
 */
@Component
public class MailProperties {
	@Value("${mail.host}")
	private String host;
	@Value("${mail.username}")
	private String userName;
	@Value("${mail.authcode}")
	private String authCode;
	@Value("${mail.SSL_FACTORY}")
	private String sslFactory;
	@Value("${mail.fallback}")
	private String fallback;
	@Value("${mail.port}")
	private String port;
	@Value("${socketFactory.port}")
	private String factoryPort;
	@Value("${mail.smtp.auth}")
	private String auth;
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getAuthCode() {
		return authCode;
	}
	
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public String getSslFactory() {
		return sslFactory;
	}
	
	public void setSslFactory(String sslFactory) {
		this.sslFactory = sslFactory;
	}
	
	public String getFallback() {
		return fallback;
	}
	
	public void setFallback(String fallback) {
		this.fallback = fallback;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getFactoryPort() {
		return factoryPort;
	}
	
	public void setFactoryPort(String factoryPort) {
		this.factoryPort = factoryPort;
	}
	
	public String getAuth() {
		return auth;
	}
	
	public void setAuth(String auth) {
		this.auth = auth;
	}
}
