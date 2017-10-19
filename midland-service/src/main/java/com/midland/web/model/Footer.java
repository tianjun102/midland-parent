package com.midland.web.model;


public class Footer{
	/**
	 * 网站页脚配置表
	 **/
	private Integer id;
	/**
	 * 注册协议
	 **/
	private String registrationProtocol;
	/**
	 * 免责声明
	 **/
	private String disclaimer;
	/**
	 * 备案编号
	 **/
	private String recordNumber;
	/**
	 * 人才招聘
	 **/
	private String recruitment;
	/**
	 * 关于我们
	 **/
	private String aboutUs;
	/**
	 * 隐私政策
	 **/
	private String privacy;
	/**
	 * 服务范围
	 **/
	private String serviceArea;
	/**
	 * 买卖流程
	 **/
	private String tradingProcess;
	/**
	 * 是否外销0=外销；1=非外销；2精英会
	 **/
	private Integer isexport;
	/**
	 * 精英会宗旨
	 **/
	private String purpose;
	/**
	 * 精英会简介
	 **/
	private String eliteDesc;
	/**
	 * 入会资格
	 **/
	private String memberShip;
	/**
	 * 发展前沿
	 **/
	private String development;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;

/**
	 * 外销网 联系我们
	 **/
	private String contactUs;

	public String getContactUs() {
		return contactUs;
	}

	public void setContactUs(String contactUs) {
		this.contactUs = contactUs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegistrationProtocol() {
		return registrationProtocol;
	}

	public void setRegistrationProtocol(String registrationProtocol) {
		this.registrationProtocol = registrationProtocol;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getRecruitment() {
		return recruitment;
	}

	public void setRecruitment(String recruitment) {
		this.recruitment = recruitment;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}

	public String getTradingProcess() {
		return tradingProcess;
	}

	public void setTradingProcess(String tradingProcess) {
		this.tradingProcess = tradingProcess;
	}

	public Integer getIsexport() {
		return isexport;
	}

	public void setIsexport(Integer isexport) {
		this.isexport = isexport;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getEliteDesc() {
		return eliteDesc;
	}

	public void setEliteDesc(String eliteDesc) {
		this.eliteDesc = eliteDesc;
	}

	public String getMemberShip() {
		return memberShip;
	}

	public void setMemberShip(String memberShip) {
		this.memberShip = memberShip;
	}

	public String getDevelopment() {
		return development;
	}

	public void setDevelopment(String development) {
		this.development = development;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Footer{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (registrationProtocol != null) {
			sb.append(", \"registrationProtocol\":\"").append(registrationProtocol).append("\"");
		}
		if (disclaimer != null) {
			sb.append(", \"disclaimer\":\"").append(disclaimer).append("\"");
		}
		if (recordNumber != null) {
			sb.append(", \"recordNumber\":\"").append(recordNumber).append("\"");
		}
		if (recruitment != null) {
			sb.append(", \"recruitment\":\"").append(recruitment).append("\"");
		}
		if (aboutUs != null) {
			sb.append(", \"aboutUs\":\"").append(aboutUs).append("\"");
		}
		if (privacy != null) {
			sb.append(", \"privacy\":\"").append(privacy).append("\"");
		}
		if (serviceArea != null) {
			sb.append(", \"serviceArea\":\"").append(serviceArea).append("\"");
		}
		if (tradingProcess != null) {
			sb.append(", \"tradingProcess\":\"").append(tradingProcess).append("\"");
		}
		if (isexport != null) {
			sb.append(", \"isexport\":\"").append(isexport).append("\"");
		}
		if (purpose != null) {
			sb.append(", \"purpose\":\"").append(purpose).append("\"");
		}
		if (eliteDesc != null) {
			sb.append(", \"eliteDesc\":\"").append(eliteDesc).append("\"");
		}
		if (memberShip != null) {
			sb.append(", \"memberShip\":\"").append(memberShip).append("\"");
		}
		if (development != null) {
			sb.append(", \"development\":\"").append(development).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		return sb.toString();
	}
}