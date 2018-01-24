package com.midland.web.model;


public class RegistrationProtocol{
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
		 final StringBuffer sb=new StringBuffer("RegistrationProtocol{");
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