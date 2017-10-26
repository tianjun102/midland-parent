package com.midland.web.model;


public class MenuType{
	/**
	 * parent_id为0时是根节点
	 **/
	private Integer id;
	/**
	 * 类型名称
	 **/
	private String name;
	/**
	 * 父类型id
	 **/
	private Integer parentId;
	/**
	 * 父类型名称
	 **/
	private String parentName;
	/**
	 * 逻辑删除，0未删除，1已删除
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("MenuType{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (name != null) {
			sb.append(", \"name\":\"").append(name).append("\"");
		}
		if (parentId != null) {
			sb.append(", \"parentId\":\"").append(parentId).append("\"");
		}
		if (parentName != null) {
			sb.append(", \"parentName\":\"").append(parentName).append("\"");
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
		sb.append("}");
		return sb.toString();
	}
}