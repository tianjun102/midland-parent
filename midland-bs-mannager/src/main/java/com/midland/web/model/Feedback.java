package com.midland.web.model;

public class Feedback{
	/**
	 * 反馈信息表
	 **/
	private Integer id;
	/**
	 * 用户昵称
	 **/
	private String nickName;
	/**
	 * 手机号码
	 **/
	private String phone;
	/**
	 * 
	 **/
	private String feedbackContent;
	/**
	 * 反馈时间
	 **/
	private String addTime;
	/**
	 * 状态 0=已取消；1=已完成；2=处理中
	 **/
	private Integer status;
	/**
	 * 备注
	 **/
	private String remark;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;
	/**
	 * 主旨：0楼盘资料，1刊登广告，2精英招聘，3客户服务，4其他
	 **/
	private String purpose;
	/**
	 * 用户id
	 **/
	private Integer userId;
	/**
	 * 操作人
	 **/
	private Integer operatorId;
	/**
	 * 操作人用户名
	 **/
	private String operatorName;
	/**
	 * 开始时间
	 **/
	private String startTime;
	/**
	 * 结束时间
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Feedback{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (nickName != null) {
			sb.append(", \"nickName\":\"").append(nickName).append("\"");
		}
		if (phone != null) {
			sb.append(", \"phone\":\"").append(phone).append("\"");
		}
		if (feedbackContent != null) {
			sb.append(", \"feedbackContent\":\"").append(feedbackContent).append("\"");
		}
		if (addTime != null) {
			sb.append(", \"addTime\":\"").append(addTime).append("\"");
		}
		if (status != null) {
			sb.append(", \"status\":\"").append(status).append("\"");
		}
		if (remark != null) {
			sb.append(", \"remark\":\"").append(remark).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (purpose != null) {
			sb.append(", \"purpose\":\"").append(purpose).append("\"");
		}
		if (userId != null) {
			sb.append(", \"userId\":\"").append(userId).append("\"");
		}
		if (operatorId != null) {
			sb.append(", \"operatorId\":\"").append(operatorId).append("\"");
		}
		if (operatorName != null) {
			sb.append(", \"operatorName\":\"").append(operatorName).append("\"");
		}
		return sb.toString();
	}
}