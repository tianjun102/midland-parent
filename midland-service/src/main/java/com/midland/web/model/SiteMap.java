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
	private String cityId;
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
	/**
	 * 是否删除
	 **/
	private Integer isDelete;
	/**
	 * 排序
	 **/
	private Integer orderBy;
	/**
	 * 是否显示
	 **/
	private Integer isShow;
	/**
	 * 平台来源
	 **/
	private Integer source;


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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}


	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
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
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (orderBy != null) {
			sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
		}
		if (isShow != null) {
			sb.append(", \"isShow\":\"").append(isShow).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		return sb.toString();
	}
}