package com.midland.web.model;

public class QuotationSecondHandView {
	
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
	private Integer preNum;
	/**
	 * 上个月成交面积
	 **/
	private String preAcreage;
	/**
	 * 成交数量
	 **/
	private Integer dealNum;
	/**
	 * 成交面积
	 **/
	private String dealAcreage;
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
	
	public Integer getPreNum() {
		return preNum;
	}
	
	public void setPreNum(Integer preNum) {
		this.preNum = preNum;
	}
	
	public String getPreAcreage() {
		return preAcreage;
	}
	
	public void setPreAcreage(String preAcreage) {
		this.preAcreage = preAcreage;
	}
	
	public Integer getDealNum() {
		return dealNum;
	}
	
	public void setDealNum(Integer dealNum) {
		this.dealNum = dealNum;
	}
	
	public String getDealAcreage() {
		return dealAcreage;
	}
	
	public void setDealAcreage(String dealAcreage) {
		this.dealAcreage = dealAcreage;
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