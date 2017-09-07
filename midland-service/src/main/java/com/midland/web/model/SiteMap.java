package com.midland.web.model;


public class SiteMap{
	/**
	 * 网站地图主键id
	 **/
	private Integer id;
	/**
	 * 名字
	 **/
	private String name;
	/**
	 * 模块id
	 **/
	private Integer modeId;
	/**
	 * 模块名称
	 **/
	private String modeName;
	/**
	 * 城市id
	 **/
	private Integer cityId;
	/**
	 * 城市名称
	 **/
	private String cityName;
	/**
	 * 分类id
	 **/
	private Integer cateId;
	/**
	 * 分类名称
	 **/
	private String cateName;
	/**
	 * 链接地址
	 **/
	private String linkUrl;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("SiteMap{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (name != null) {
			sb.append(", \"name\":\"").append(name).append("\"");
		}
		if (modeId != null) {
			sb.append(", \"modeId\":\"").append(modeId).append("\"");
		}
		if (modeName != null) {
			sb.append(", \"modeName\":\"").append(modeName).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (cateId != null) {
			sb.append(", \"cateId\":\"").append(cateId).append("\"");
		}
		if (cateName != null) {
			sb.append(", \"cateName\":\"").append(cateName).append("\"");
		}
		if (linkUrl != null) {
			sb.append(", \"linkUrl\":\"").append(linkUrl).append("\"");
		}
		return sb.toString();
	}
}