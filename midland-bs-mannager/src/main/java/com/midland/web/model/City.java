package com.midland.web.model;


public class City{
	/**
	 * 城市表主键
	 **/
	private Integer id;
	/**
	 * 城市名称
	 **/
	private String cityName;
	/**
	 * 父节点id；城市/区域
	 **/
	private Integer parentId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("City{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (parentId != null) {
			sb.append(", \"parentId\":\"").append(parentId).append("\"");
		}
		return sb.toString();
	}
}