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
}