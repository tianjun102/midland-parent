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
	 * 售价/租价
	 **/
	private String price;
	/**
	 * 委托时间
	 **/
	private String entrustTime;
	/**
	 * 经纪人id
	 **/
	private Integer userId;
	/**
	 * 经纪人名字
	 **/
	private String userCnName;
	/**
	 * 状态；0=未处理；1=处理中；2已完成
	 **/
	private Integer status;
	/**
	 * 处理时间
	 **/
	private String handleTime;
	/**
	 * 
	 **/
	private Short decoration;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;
    /**
     * 前端备注
     **/
    private String remark;
    
    
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

	public String getEntrustTime() {
		return entrustTime;
	}

	public void setEntrustTime(String entrustTime) {
		this.entrustTime = entrustTime;
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

	public Short getDecoration() {
		return decoration;
	}

	public void setDecoration(Short decoration) {
		this.decoration = decoration;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
		if (entrustTime != null) {
			sb.append(", \"entrustTime\":\"").append(entrustTime).append("\"");
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
		if (decoration != null) {
			sb.append(", \"decoration\":\"").append(decoration).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		return sb.toString();
	}
}