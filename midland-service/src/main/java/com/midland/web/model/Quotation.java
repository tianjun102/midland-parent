package com.midland.web.model;


public class Quotation{
	/**
	 * 行情表，主键
	 **/
	private Integer id;
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
	 * 城市名称
	 **/
	private String cityName;
	/**
	 * 区域id
	 **/
	private String areaId;
	/**
	 * 区域名称
	 **/
	private String areaName;
	/**
	 * 片区
	 **/
	private String sliceId;
	/**
	 * 片区名称
	 **/
	private String sliceName;
	/**
	 * 成交数量
	 **/
	private Integer dealNum;
	/**
	 * 成交面积
	 **/
	private String dealAcreage;
	/**
	 * 成交均价
	 **/
	private String price;
	/**
	 * 可售套数
	 **/
	private Integer soldNum;
	/**
	 * 可售面积
	 **/
	private String soldArea;
	/**
	 * 环比
	 **/
	private String ringRatio;
	/**
	 * 更新时间
	 **/
	private String updateTime;
	/**
	 * 1，新房；0，二手房
	 **/
	private Integer isNew;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;
	/**
	 * 房子面积范围（只有住宅类型的房子才有面积范围）
	 **/
	private String houseAcreage;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSliceId() {
		return sliceId;
	}

	public void setSliceId(String sliceId) {
		this.sliceId = sliceId;
	}

	public String getSliceName() {
		return sliceName;
	}

	public void setSliceName(String sliceName) {
		this.sliceName = sliceName;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getSoldNum() {
		return soldNum;
	}

	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}

	public String getSoldArea() {
		return soldArea;
	}

	public void setSoldArea(String soldArea) {
		this.soldArea = soldArea;
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

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getHouseAcreage() {
		return houseAcreage;
	}

	public void setHouseAcreage(String houseAcreage) {
		this.houseAcreage = houseAcreage;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Quotation{");
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
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (areaId != null) {
			sb.append(", \"areaId\":\"").append(areaId).append("\"");
		}
		if (areaName != null) {
			sb.append(", \"areaName\":\"").append(areaName).append("\"");
		}
		if (sliceId != null) {
			sb.append(", \"sliceId\":\"").append(sliceId).append("\"");
		}
		if (sliceName != null) {
			sb.append(", \"sliceName\":\"").append(sliceName).append("\"");
		}
		if (dealNum != null) {
			sb.append(", \"dealNum\":\"").append(dealNum).append("\"");
		}
		if (dealAcreage != null) {
			sb.append(", \"dealAcreage\":\"").append(dealAcreage).append("\"");
		}
		if (price != null) {
			sb.append(", \"price\":\"").append(price).append("\"");
		}
		if (soldNum != null) {
			sb.append(", \"soldNum\":\"").append(soldNum).append("\"");
		}
		if (soldArea != null) {
			sb.append(", \"soldArea\":\"").append(soldArea).append("\"");
		}
		if (ringRatio != null) {
			sb.append(", \"ringRatio\":\"").append(ringRatio).append("\"");
		}
		if (updateTime != null) {
			sb.append(", \"updateTime\":\"").append(updateTime).append("\"");
		}
		if (isNew != null) {
			sb.append(", \"isNew\":\"").append(isNew).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (houseAcreage != null) {
			sb.append(", \"houseAcreage\":\"").append(houseAcreage).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}