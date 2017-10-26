package com.midland.web.model;


public class Record{
	/**
	 * 备案记录id
	 **/
	private Integer id;
	/**
	 * 新增时间
	 **/
	private String addTime;
	/**
	 * 上次修改时间
	 **/
	private String updateTime;
	/**
	 * 城市id
	 **/
	private String cityId;
	/**
	 * 城市名称
	 **/
	private String cityName;
	/**
	 * 备案信息
	 **/
	private String record;
	/**
	 * 是否删除，0未删除，1已删除
	 **/
	private String isDelete;
	/**
	 * 0显示，1隐藏
	 **/
	private Integer isShow;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Record{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (addTime != null) {
			sb.append(", \"addTime\":\"").append(addTime).append("\"");
		}
		if (updateTime != null) {
			sb.append(", \"updateTime\":\"").append(updateTime).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (record != null) {
			sb.append(", \"record\":\"").append(record).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (isShow != null) {
			sb.append(", \"isShow\":\"").append(isShow).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}