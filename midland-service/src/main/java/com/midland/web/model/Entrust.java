package com.midland.web.model;

public class Entrust {
	/**
	 * 委托表主键id；
	 **/
	private Integer id;
	/**
	 * 委托编号
	 **/
	private String entrustSn;
	/**
	 * 网站来源；0=网站；1=微站；
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
	private String area;
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
	private Integer userId;
	/**
	 * 经纪人名字
	 **/
	private String userCnName;
	/**
	 * 状态；0=未处理；1=处理中
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
	 * 0=简装；1=精装
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
	 * 宽带 ： 0没有，1有
	 **/
	private Integer broadband;
	/**
	 * 洗衣机：0没有，1有
	 **/
	private Integer washingMachine;
	/**
	 * 家电：0没有，1有
	 **/
	private Integer household;
	/**
	 * 冰箱：0没有，1有
	 **/
	private Integer fridge;
	/**
	 * 备注
	 **/
	private String remark;
	
	private String startTime;
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
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
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
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getUserCnName() {
		return userCnName;
	}
	
	public void setUserCnName(String userCnName) {
		this.userCnName = userCnName;
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
	
	public Integer getBroadband() {
		return broadband;
	}
	
	public void setBroadband(Integer broadband) {
		this.broadband = broadband;
	}
	
	public Integer getWashingMachine() {
		return washingMachine;
	}
	
	public void setWashingMachine(Integer washingMachine) {
		this.washingMachine = washingMachine;
	}
	
	public Integer getHousehold() {
		return household;
	}
	
	public void setHousehold(Integer household) {
		this.household = household;
	}
	
	public Integer getFridge() {
		return fridge;
	}
	
	public void setFridge(Integer fridge) {
		this.fridge = fridge;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Entrust{");
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
		if (area != null) {
			sb.append(", \"area\":\"").append(area).append("\"");
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
		if (userId != null) {
			sb.append(", \"userId\":\"").append(userId).append("\"");
		}
		if (userCnName != null) {
			sb.append(", \"userCnName\":\"").append(userCnName).append("\"");
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
		if (renovation != null) {
			sb.append(", \"renovation\":\"").append(renovation).append("\"");
		}
		if (phone != null) {
			sb.append(", \"phone\":\"").append(phone).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (broadband != null) {
			sb.append(", \"broadband\":\"").append(broadband).append("\"");
		}
		if (washingMachine != null) {
			sb.append(", \"washingMachine\":\"").append(washingMachine).append("\"");
		}
		if (household != null) {
			sb.append(", \"household\":\"").append(household).append("\"");
		}
		if (fridge != null) {
			sb.append(", \"fridge\":\"").append(fridge).append("\"");
		}
		return sb.toString();
	}
}