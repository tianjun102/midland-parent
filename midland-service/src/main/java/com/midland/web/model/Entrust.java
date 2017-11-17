package com.midland.web.model;


public class Entrust{
	/**
	 * 委托表主键id；
	 **/
	private Integer id;
	/**
	 * 委托编号
	 **/
	private String entrustSn;
	/**
	 * 平台；0=网站；1=微站；
	 **/
	private Integer source;
	/**
	 * 委托房屋类型：0新房，1二手房，2租房，3写字楼，4商铺，5其它
	 **/
	private Integer houseType;
	/**
	 * 0=租；1=售
	 **/
	private Integer sellRent;
	/**
	 * 委托时间
	 **/
	private String entrustTime;
	/**
	 * 所属区域
	 **/
	private String areaName;
	/**
	 * 小区名字
	 **/
	private String communityName;
	/**
	 * 门牌地址
	 **/
	private String address;
	/**
	 * 户型
	 **/
	private String layout;
	/**
	 * 面积
	 **/
	private String measure;
	/**
	 * 价格
	 **/
	private String price;
	/**
	 * 用户id
	 **/
	private String agentId;
	/**
	 * 经纪人名字
	 **/
	private String agentName;
	/**
	 * 状态；0=待分配；1=已分配；2看房中，3已取消，4议价中，5已成交
	 **/
	private Integer status;
	/**
	 * 处理时间
	 **/
	private String handleTime;
	/**
	 * 称呼
	 **/
	private String nickName;
	/**
	 * 委托人
	 */
	private String userId;

	/**
	 * 0豪装；1精装，2普装，3毛坯
	 **/
	private Integer renovation;
	/**
	 * 手机号码
	 **/
	private String phone;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;
	/**
	 * 其他设备：0冰箱,1电视,2空调,3宽带,4家具,5厨房,6热水器,7洗衣机,8暖气,9阳台,10煤气
	 **/
	private String otherFacilities;
	/**
	 * 买房委托类型：0我要买房，1我要卖房，2我要出租，3我要租房，4我要估价
	 **/
	private Integer entrustType;
	/**
	 * 城市id
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
	 * 经纪人手机号
	 **/
	private String agentPhone;
	/**
	 * 是否展示重新分配按钮，0不，1是
	 **/
	private Integer resetFlag;
	/**
	 * 分配经纪人时间
	 **/
	private String assignedTime;
	/**
	 * 房屋朝向,0东，1东南，2南，3西南，4西，5西北，6北，7东北
	 **/
	private String turned;
	/**
	 * 建造年份
	 **/
	private String buildYear;
	/**
	 * 所属层
	 **/
	private String theLayer;
	/**
	 * 
	 **/
	private String totalLayer;
	/**
	 * 其它特色，0满五，1唯一，2精装，3地铁房
	 **/
	private String otherItem;
	/**
	 * 备注
	 **/
	private String remark;

	private String startTime;

	private String endTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntrustSn() {
		return entrustSn;
	}

	public void setEntrustSn(String entrustSn) {
		this.entrustSn = entrustSn;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}

	public Integer getSellRent() {
		return sellRent;
	}

	public void setSellRent(Integer sellRent) {
		this.sellRent = sellRent;
	}

	public String getEntrustTime() {
		return entrustTime;
	}

	public void setEntrustTime(String entrustTime) {
		this.entrustTime = entrustTime;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getRenovation() {
		return renovation;
	}

	public void setRenovation(Integer renovation) {
		this.renovation = renovation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getOtherFacilities() {
		return otherFacilities;
	}

	public void setOtherFacilities(String otherFacilities) {
		this.otherFacilities = otherFacilities;
	}

	public Integer getEntrustType() {
		return entrustType;
	}

	public void setEntrustType(Integer entrustType) {
		this.entrustType = entrustType;
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

	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}

	public Integer getResetFlag() {
		return resetFlag;
	}

	public void setResetFlag(Integer resetFlag) {
		this.resetFlag = resetFlag;
	}

	public String getAssignedTime() {
		return assignedTime;
	}

	public void setAssignedTime(String assignedTime) {
		this.assignedTime = assignedTime;
	}

	public String getTurned() {
		return turned;
	}

	public void setTurned(String turned) {
		this.turned = turned;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getTheLayer() {
		return theLayer;
	}

	public void setTheLayer(String theLayer) {
		this.theLayer = theLayer;
	}

	public String getTotalLayer() {
		return totalLayer;
	}

	public void setTotalLayer(String totalLayer) {
		this.totalLayer = totalLayer;
	}

	public String getOtherItem() {
		return otherItem;
	}

	public void setOtherItem(String otherItem) {
		this.otherItem = otherItem;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Entrust{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (entrustSn != null) {
			sb.append(", \"entrustSn\":\"").append(entrustSn).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		if (houseType != null) {
			sb.append(", \"houseType\":\"").append(houseType).append("\"");
		}
		if (sellRent != null) {
			sb.append(", \"sellRent\":\"").append(sellRent).append("\"");
		}
		if (entrustTime != null) {
			sb.append(", \"entrustTime\":\"").append(entrustTime).append("\"");
		}
		if (areaName != null) {
			sb.append(", \"areaName\":\"").append(areaName).append("\"");
		}
		if (communityName != null) {
			sb.append(", \"communityName\":\"").append(communityName).append("\"");
		}
		if (address != null) {
			sb.append(", \"address\":\"").append(address).append("\"");
		}
		if (layout != null) {
			sb.append(", \"layout\":\"").append(layout).append("\"");
		}
		if (measure != null) {
			sb.append(", \"measure\":\"").append(measure).append("\"");
		}
		if (price != null) {
			sb.append(", \"price\":\"").append(price).append("\"");
		}
		if (agentId != null) {
			sb.append(", \"agentId\":\"").append(agentId).append("\"");
		}
		if (agentName != null) {
			sb.append(", \"agentName\":\"").append(agentName).append("\"");
		}
		if (status != null) {
			sb.append(", \"status\":\"").append(status).append("\"");
		}
		if (handleTime != null) {
			sb.append(", \"handleTime\":\"").append(handleTime).append("\"");
		}
		if (nickName != null) {
			sb.append(", \"nickName\":\"").append(nickName).append("\"");
		}
		if (userId != null) {
			sb.append(", \"userId\":").append(userId);
		}
		if (renovation != null) {
			sb.append(", \"renovation\":\"").append(renovation).append("\"");
		}
		if (phone != null) {
			sb.append(", \"phone\":\"").append(phone).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (otherFacilities != null) {
			sb.append(", \"otherFacilities\":\"").append(otherFacilities).append("\"");
		}
		if (entrustType != null) {
			sb.append(", \"entrustType\":\"").append(entrustType).append("\"");
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
		if (agentPhone != null) {
			sb.append(", \"agentPhone\":\"").append(agentPhone).append("\"");
		}
		if (resetFlag != null) {
			sb.append(", \"resetFlag\":\"").append(resetFlag).append("\"");
		}
		if (assignedTime != null) {
			sb.append(", \"assignedTime\":\"").append(assignedTime).append("\"");
		}
		if (turned != null) {
			sb.append(", \"turned\":\"").append(turned).append("\"");
		}
		if (buildYear != null) {
			sb.append(", \"buildYear\":\"").append(buildYear).append("\"");
		}
		if (theLayer != null) {
			sb.append(", \"theLayer\":\"").append(theLayer).append("\"");
		}
		if (totalLayer != null) {
			sb.append(", \"totalLayer\":\"").append(totalLayer).append("\"");
		}
		if (otherItem != null) {
			sb.append(", \"otherItem\":\"").append(otherItem).append("\"");
		}
		if (remark != null) {
			sb.append(", \"remark\":\"").append(remark).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}