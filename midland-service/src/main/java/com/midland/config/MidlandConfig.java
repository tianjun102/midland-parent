package com.midland.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 'ms.x' on 2017/9/14.
 */
@Component
public class MidlandConfig {
	@Value("${loginUrl}")
	private String loginUrl;
	@Value("${apiUrl}")
	private String apiUrl;
	@Value("${areaUrl}")
	private String areaUrl;
	@Value("${agentPage}")
	private String agentPage;
	@Value("${agentLogin}")
	private String agentLogin;
	@Value("${agentDetail}")
	private String agentDetail;

	@Value("${webSite}")
	private String webSite;

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getAgentPage() {
		return agentPage;
	}
	
	public void setAgentPage(String agentPage) {
		this.agentPage = agentPage;
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}
	
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	
	public String getApiUrl() {
		return apiUrl;
	}
	
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public String getAreaUrl() {
		return areaUrl;
	}
	
	public void setAreaUrl(String areaUrl) {
		this.areaUrl = areaUrl;
	}

	public String getAgentLogin() {
		return agentLogin;
	}

	public String getAgentDetail() {
		return agentDetail;
	}
}
