package com.midland.web.model;

/**
 * 网站协议
 */
public class SiteProtocol{
	/**
	 * 
	 **/
	private Integer id;
	/**
	 * 
	 **/
	private Integer source;
	/**
	 * 
	 **/
	private String cityId;
	/**
	 * 
	 **/
	private String cityName;
	/**
	 * 免责申明
	 **/
	private String disclaimer;
	/**
	 * 联系我们
	 **/
	private String contantUs;
	/**
	 * 关于美联
	 **/
	private String aboutUs;
	/**
	 * 脚文件
	 **/
	private String cornerFile;
	/**
	 * 私隐政策
	 **/
	private String privacy;

	/**
	 * 美联荣誉
	 */
	private String honor;
	/**
	 * 注册协议
	 **/
	private String registrationProtocol;
	/**
	 * 创建时间
	 **/
	private String createTime;
	/**
	 * 删除 0正常,1删除
	 **/
	private Integer isDelete;


	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getContantUs() {
		return contantUs;
	}

	public void setContantUs(String contantUs) {
		this.contantUs = contantUs;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public String getCornerFile() {
		return cornerFile;
	}

	public void setCornerFile(String cornerFile) {
		this.cornerFile = cornerFile;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getRegistrationProtocol() {
		return registrationProtocol;
	}

	public void setRegistrationProtocol(String registrationProtocol) {
		this.registrationProtocol = registrationProtocol;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("SiteProtocol{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (disclaimer != null) {
			sb.append(", \"disclaimer\":\"").append(disclaimer).append("\"");
		}
		if (contantUs != null) {
			sb.append(", \"contantUs\":\"").append(contantUs).append("\"");
		}
		if (aboutUs != null) {
			sb.append(", \"aboutUs\":\"").append(aboutUs).append("\"");
		}
		if (cornerFile != null) {
			sb.append(", \"cornerFile\":\"").append(cornerFile).append("\"");
		}
		if (privacy != null) {
			sb.append(", \"privacy\":\"").append(privacy).append("\"");
		}
		if (registrationProtocol != null) {
			sb.append(", \"registrationProtocol\":\"").append(registrationProtocol).append("\"");
		}
		if (createTime != null) {
			sb.append(", \"createTime\":\"").append(createTime).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}