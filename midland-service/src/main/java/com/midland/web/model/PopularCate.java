package com.midland.web.model;


public class PopularCate{
	/**
	 * 热门关注分类主键id
	 **/
	private Integer id;
	/**
	 * 分类名称
	 **/
	private String cateName;
	/**
	 * 0=显示；1=隐藏
	 **/
	private Integer isShow;
	/**
	 * 0=显示；1=隐藏
	 **/
	private Integer isDelete;
	/**
	 * 城市id
	 **/
	private String cityId;
	/**
	 * 城市名称
	 **/
	private String cityName;
	/**
	 * 0=网站；1=微站
	 **/
	private Integer source;
	/**
	 * 模块id
	 **/
	private Integer menuId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("PopularCate{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (cateName != null) {
			sb.append(", \"cateName\":\"").append(cateName).append("\"");
		}
		if (isShow != null) {
			sb.append(", \"isShow\":\"").append(isShow).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		if (menuId != null) {
			sb.append(", \"menuId\":\"").append(menuId).append("\"");
		}
		return sb.toString();
	}
}