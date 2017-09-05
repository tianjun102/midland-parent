package com.midland.web.model;


public class LeaveMsg{
	/**
	 * 留言表主键
	 **/
	private Integer id;
	/**
	 * 留言
	 **/
	private String message;
	/**
	 * 留言时间
	 **/
	private String addTime;
	/**
	 * 用户id
	 **/
	private Integer userId;
	/**
	 * 用户名称
	 **/
	private String userName;
	/**
	 * 手机号码
	 **/
	private String phone;
	/**
	 * 回复类容
	 **/
	private String replyMsg;
	/**
	 * 0=求购；1=求租；2=其它
	 **/
	private Integer type;
	/**
	 * 删除：0未删除，1已删除
	 **/
	private Integer isDelete;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("LeaveMsg{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (message != null) {
			sb.append(", \"message\":\"").append(message).append("\"");
		}
		if (addTime != null) {
			sb.append(", \"addTime\":\"").append(addTime).append("\"");
		}
		if (userId != null) {
			sb.append(", \"userId\":\"").append(userId).append("\"");
		}
		if (userName != null) {
			sb.append(", \"userName\":\"").append(userName).append("\"");
		}
		if (phone != null) {
			sb.append(", \"phone\":\"").append(phone).append("\"");
		}
		if (replyMsg != null) {
			sb.append(", \"replyMsg\":\"").append(replyMsg).append("\"");
		}
		if (type != null) {
			sb.append(", \"type\":\"").append(type).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		return sb.toString();
	}
}