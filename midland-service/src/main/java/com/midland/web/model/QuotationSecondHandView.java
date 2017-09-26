package com.midland.web.model;

public class QuotationSecondHandView {
	
	
	
	
	/**
	 * 行情表，主键
	 **/
	private Integer id;

	/**
	 * 片区
	 **/
	private String sliceId;

	/**
	 * 环比
	 **/
	private String ringRatio;
	/**
	 * 更新时间
	 **/
	private String updateTime;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;
	/**
	 * 城市名称
	 **/
	private String cityName;
	/**
	 * 区域名称
	 **/
	private String areaName;
	/**
	 * 片区名称
	 **/
	private String sliceName;
	/**
	 * 数据时间
	 **/
	private String dataTime;
	/**
	 * 类型
	 **/
	private Integer type;
	/**
	 * 
	 **/
	private String cityId;
	/**
	 * 区域id
	 **/
	private String areaId;
	/**
	 * 上个月成交数量
	 **/
	private Double preNum;
	/**
	 * 上个月成交面积
	 **/
	private Double preAcreage;
	/**
	 * 成交数量
	 **/
	private Integer dealNum;
	/**
	 * 成交面积
	 **/
	private Double dealAcreage;
	/**
	 * 开始时间
	 **/
	private String startTime;
	/**
	 * 截至时间
	 **/
	private String endTime;
	
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getDataTime() {
		return dataTime;
	}
	
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getCityId() {
		return cityId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getAreaId() {
		return areaId;
	}
	
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public Double getPreNum() {
		return preNum;
	}
	
	public void setPreNum(Double preNum) {
		this.preNum = preNum;
	}
	
	public Double getPreAcreage() {
		return preAcreage;
	}
	
	public void setPreAcreage(Double preAcreage) {
		this.preAcreage = preAcreage;
	}
	
	public Integer getDealNum() {
		return dealNum;
	}
	
	public void setDealNum(Integer dealNum) {
		this.dealNum = dealNum;
	}
	
	public Double getDealAcreage() {
		return dealAcreage;
	}
	
	public void setDealAcreage(Double dealAcreage) {
		this.dealAcreage = dealAcreage;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSliceId() {
		return sliceId;
	}
	
	public void setSliceId(String sliceId) {
		this.sliceId = sliceId;
	}
	
	public String getRingRatio() {
		return ringRatio;
	}
	
	public void setRingRatio(String ringRatio) {
		this.ringRatio = ringRatio;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getSliceName() {
		return sliceName;
	}
	
	public void setSliceName(String sliceName) {
		this.sliceName = sliceName;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("QuotationSecondHandView{");
		sb.append("dataTime=").append(dataTime);
		sb.append(", type=").append(type);
		sb.append(", cityId='").append(cityId).append('\'');
		sb.append(", areaId='").append(areaId).append('\'');
		sb.append(", preNum=").append(preNum);
		sb.append(", preAcreage='").append(preAcreage).append('\'');
		sb.append(", dealNum=").append(dealNum);
		sb.append(", dealAcreage='").append(dealAcreage).append('\'');
		sb.append('}');
		return sb.toString();
	}
}