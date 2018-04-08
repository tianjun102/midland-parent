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
	private String title;
	/**
	 * 
	 **/
	private String keywords;
	/**
	 * 
	 **/
	private String description;
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
	 * 子模块id
	 **/
	private Integer secondModeId;


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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getSecondModeId() {
		return secondModeId;
	}

	public void setSecondModeId(Integer secondModeId) {
		this.secondModeId = secondModeId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Meta{");
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
		if (title != null) {
			sb.append(", \"title\":\"").append(title).append("\"");
		}
		if (keywords != null) {
			sb.append(", \"keywords\":\"").append(keywords).append("\"");
		}
		if (description != null) {
			sb.append(", \"description\":\"").append(description).append("\"");
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
		if (secondModeId != null) {
			sb.append(", \"secondModeId\":\"").append(secondModeId).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}