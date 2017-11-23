package com.midland.web.model;


public class Appointment{
	/**
	 * 预约记录表ID，采用自增长
	 **/
	private Integer id;
	/**
	 * 预约编号
	 **/
	private String appointSn;
	/**
	 * 0=网站；1=微站
	 **/
	private Integer source;
	/**
	 * 
	 **/
	private String nickName;
	/**
	 * 手机号码
	 **/
	private String phone;
	/**
	 * 0=住宅；1=公寓；2=写字楼；3=商铺
	 **/
	private Integer houseType;

	/**
	 * 房源id
	 **/
	private String houseId;
	/**
	 * 0=租；1=售
	 **/
	private Integer sellRent;
	/**
	 * 预约时间
	 **/
	private String appointmentTime;
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
	 * 售价/租价
	 **/
	private String price;
	/**
	 * 委托时间
	 **/
	private String entrustTime;
	/**
	 * 经纪人工号
	 **/
	private String agentId;
	/**
	 * 经纪人名字
	 **/
	private String agentName;
	/**
	 * 状态；0=未处理；1=处理中；2已完成
	 **/
	private Integer status;
	/**
	 * 处理时间
	 **/
	private String handleTime;
	/**
	 * 装潢
	 **/
	private String decoration;
	/**
	 * 0未删除，1删除
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
	 * 区域id
	 **/
	private Integer areaId;
	/**
	 * 24小时状态没修改时发起提醒成功：0未发送，1已发送
	 **/
	private Integer flag;
	/**
	 * 经纪人手机号
	 **/
	private String agentPhone;
	/**
	 * 3D看房url
	 **/
	private String viewUrl;
	/**
	 * 是否展示重新分配按钮，0不，1是
	 **/
	private Integer resetFlag;
	/**
	 * 分配经纪人时间
	 **/
	private String assignedTime;
	private String startTime;
	private String endTime;
	/**
	 * 委托人性别，0女1男
	 */
	private Integer sex;
	/**
	 * 前端用户id
	 */
	private String webUserId;

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getWebUserId() {
		return webUserId;
	}

	public void setWebUserId(String webUserId) {
		this.webUserId = webUserId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public String getAppointSn() {
		return appointSn;
	}

	public void setAppointSn(String appointSn) {
		this.appointSn = appointSn;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
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

	public String getEntrustTime() {
		return entrustTime;
	}

	public void setEntrustTime(String entrustTime) {
		this.entrustTime = entrustTime;
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

	public String getDecoration() {
		return decoration;
	}

	public void setDecoration(String decoration) {
		this.decoration = decoration;
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

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
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

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Appointment{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (appointSn != null) {
			sb.append(", \"appointSn\":\"").append(appointSn).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		if (webUserId != null) {
			sb.append(", \"webUserId\":\"").append(webUserId).append("\"");
		}
		if (nickName != null) {
			sb.append(", \"nickName\":\"").append(nickName).append("\"");
		}
		if (phone != null) {
			sb.append(", \"phone\":\"").append(phone).append("\"");
		}
		if (houseType != null) {
			sb.append(", \"houseType\":\"").append(houseType).append("\"");
		}
		if (sellRent != null) {
			sb.append(", \"sellRent\":\"").append(sellRent).append("\"");
		}
		if (appointmentTime != null) {
			sb.append(", \"appointmentTime\":\"").append(appointmentTime).append("\"");
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
		if (entrustTime != null) {
			sb.append(", \"entrustTime\":\"").append(entrustTime).append("\"");
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
		if (decoration != null) {
			sb.append(", \"decoration\":\"").append(decoration).append("\"");
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
		if (areaId != null) {
			sb.append(", \"areaId\":\"").append(areaId).append("\"");
		}
		if (flag != null) {
			sb.append(", \"flag\":\"").append(flag).append("\"");
		}
		if (agentPhone != null) {
			sb.append(", \"agentPhone\":\"").append(agentPhone).append("\"");
		}
		if (viewUrl != null) {
			sb.append(", \"viewUrl\":\"").append(viewUrl).append("\"");
		}
		if (resetFlag != null) {
			sb.append(", \"resetFlag\":\"").append(resetFlag).append("\"");
		}
		if (assignedTime != null) {
			sb.append(", \"assignedTime\":\"").append(assignedTime).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}