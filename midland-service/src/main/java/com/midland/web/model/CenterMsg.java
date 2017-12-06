package com.midland.web.model;


public class CenterMsg{
	/**
	 * 消息中心主键
	 **/
	private Integer id;
	/**
	 * 用户id
	 **/
	private String userId;
	/**
	 * 其它用户id
	 **/
	private String otherUserId;
	/**
	 * 0=评论；1=问答；2=关注；3=预约；4=委托
	 **/
	private Integer type;
	/**
	 * 创建时间
	 **/
	private String addTime;
	/**
	 * 消息
	 **/
	private String msg;
	/**
	 * 消息标题
	 **/
	private String title;
	/**
	 * 用户头像
	 **/
	private String headImg;
	/**
	 * 详情id
	 **/
	private String jumpId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOtherUserId() {
		return otherUserId;
	}

	public void setOtherUserId(String otherUserId) {
		this.otherUserId = otherUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getJumpId() {
		return jumpId;
	}

	public void setJumpId(String jumpId) {
		this.jumpId = jumpId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("CenterMsg{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (userId != null) {
			sb.append(", \"userId\":\"").append(userId).append("\"");
		}
		if (otherUserId != null) {
			sb.append(", \"otherUserId\":\"").append(otherUserId).append("\"");
		}
		if (type != null) {
			sb.append(", \"type\":\"").append(type).append("\"");
		}
		if (addTime != null) {
			sb.append(", \"addTime\":\"").append(addTime).append("\"");
		}
		if (msg != null) {
			sb.append(", \"msg\":\"").append(msg).append("\"");
		}
		if (title != null) {
			sb.append(", \"title\":\"").append(title).append("\"");
		}
		if (headImg != null) {
			sb.append(", \"headImg\":\"").append(headImg).append("\"");
		}
		if (jumpId != null) {
			sb.append(", \"jumpId\":\"").append(jumpId).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}