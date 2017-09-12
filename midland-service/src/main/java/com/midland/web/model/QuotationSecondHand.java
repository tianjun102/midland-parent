package com.midland.web.model;

import java.util.Date;

public class QuotationSecondHand{
	/**
	 * 行情表，主键
	 **/
	private Integer id;
	/**
	 * 数据时间
	 **/
	private Date dataTime;
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
	 * 片区
	 **/
	private String sliceId;
	/**
	 * 成交数量
	 **/
	private Integer dealNum;
	/**
	 * 成交面积
	 **/
	private String dealAcreage;
	/**
	 * 环比
	 **/
	private String ringRatio;
	/**
	 * 更新时间
	 **/
	private Date updateTime;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
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

	public String getSliceId() {
		return sliceId;
	}

	public void setSliceId(String sliceId) {
		this.sliceId = sliceId;
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

	public String getRingRatio() {
		return ringRatio;
	}

	public void setRingRatio(String ringRatio) {
		this.ringRatio = ringRatio;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
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
		 final StringBuffer sb=new StringBuffer("QuotationSecondHand{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (dataTime != null) {
			sb.append(", \"dataTime\":\"").append(dataTime).append("\"");
		}
		if (type != null) {
			sb.append(", \"type\":\"").append(type).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (areaId != null) {
			sb.append(", \"areaId\":\"").append(areaId).append("\"");
		}
		if (sliceId != null) {
			sb.append(", \"sliceId\":\"").append(sliceId).append("\"");
		}
		if (dealNum != null) {
			sb.append(", \"dealNum\":\"").append(dealNum).append("\"");
		}
		if (dealAcreage != null) {
			sb.append(", \"dealAcreage\":\"").append(dealAcreage).append("\"");
		}
		if (ringRatio != null) {
			sb.append(", \"ringRatio\":\"").append(ringRatio).append("\"");
		}
		if (updateTime != null) {
			sb.append(", \"updateTime\":\"").append(updateTime).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (areaName != null) {
			sb.append(", \"areaName\":\"").append(areaName).append("\"");
		}
		if (sliceName != null) {
			sb.append(", \"sliceName\":\"").append(sliceName).append("\"");
		}
		return sb.toString();
	}
}