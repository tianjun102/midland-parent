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
	@Value("${newhouse}")
	private String newhouse;
	@Value("${second}")
	private String second;
	@Value("${rent}")
	private String rent;
	@Value("${work}")
	private String work;
	@Value("${shop}")
	private String shop;

	public void setAgentLogin(String agentLogin) {
		this.agentLogin = agentLogin;
	}

	public void setAgentDetail(String agentDetail) {
		this.agentDetail = agentDetail;
	}

	public String getNewhouse() {
		return newhouse;
	}

	public void setNewhouse(String newhouse) {
		this.newhouse = newhouse;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

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
