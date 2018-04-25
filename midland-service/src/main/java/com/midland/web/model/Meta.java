package com.midland.web.model;


public class Meta{
	/**
	 * id
	 **/
	private Integer id;
	/**
	 * 平台,0网站,1微站
	 **/
	private Integer source;
	/**
	 * 城市id
	 **/
	private String cityId;
	/**
	 * 城市name
	 **/
	private String cityName;
	/**
	 * 
	 **/
	private String websiteTitle;
	/**
	 * 
	 **/
	private String websiteKeyWords;
	/**
	 * 
	 **/
	private String websiteDescription;
	/**
	 * 模块id
	 **/
	private Integer modeId;
	/**
	 * 模块名称
	 **/
	private String modeName;
	/**
	 * 子模块名称
	 **/
	private String secondModeName;
	/**
	 * 是否删除,0否1是
	 **/
	private Integer isDelete;
	/**
	 * 子模块id
	 **/
	private Integer secondModeId;
/**
	 * 排序
	 **/
	private Integer orderBy;

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
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

	public String getWebsiteTitle() {
		return websiteTitle;
	}

	public void setWebsiteTitle(String websiteTitle) {
		this.websiteTitle = websiteTitle;
	}

	public String getWebsiteKeyWords() {
		return websiteKeyWords;
	}

	public void setWebsiteKeyWords(String websiteKeyWords) {
		this.websiteKeyWords = websiteKeyWords;
	}

	public String getWebsiteDescription() {
		return websiteDescription;
	}

	public void setWebsiteDescription(String websiteDescription) {
		this.websiteDescription = websiteDescription;
	}

	public Integer getModeId() {
		return modeId;
	}

	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getSecondModeName() {
		return secondModeName;
	}

	public void setSecondModeName(String secondModeName) {
		this.secondModeName = secondModeName;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSecondModeId() {
		return secondModeId;
	}

	public void setSecondModeId(Integer secondModeId) {
		this.secondModeId = secondModeId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("MetaResult{");
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
		if (websiteTitle != null) {
			sb.append(", \"websiteTitle\":\"").append(websiteTitle).append("\"");
		}
		if (websiteKeyWords != null) {
			sb.append(", \"websiteKeyWords\":\"").append(websiteKeyWords).append("\"");
		}
		if (websiteDescription != null) {
			sb.append(", \"websiteDescription\":\"").append(websiteDescription).append("\"");
		}
		if (modeId != null) {
			sb.append(", \"modeId\":\"").append(modeId).append("\"");
		}
		if (modeName != null) {
			sb.append(", \"modeName\":\"").append(modeName).append("\"");
		}
		if (secondModeName != null) {
			sb.append(", \"secondModeName\":\"").append(secondModeName).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (secondModeId != null) {
			sb.append(", \"secondModeId\":\"").append(secondModeId).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}