package com.midland.web.model;

import java.util.Date;

public class Reply{
	/**
	 * 回复表主键
	 **/
	private Integer id;
	/**
	 * 回复详情
	 **/
	private String detail;
	/**
	 * 回复时间
	 **/
	private String addtime;
	/**
	 * 回复人
	 **/
	private String user;
	/**
	 * 回复人id
	 **/
	private Integer userId;
	/**
	 * 关联评论id
	 **/
	private Integer commId;

	private Integer likes;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommId() {
		return commId;
	}

	public void setCommId(Integer commId) {
		this.commId = commId;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Reply{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (detail != null) {
			sb.append(", \"detail\":\"").append(detail).append("\"");
		}
		if (addtime != null) {
			sb.append(", \"addtime\":\"").append(addtime).append("\"");
		}
		if (user != null) {
			sb.append(", \"user\":\"").append(user).append("\"");
		}
		if (userId != null) {
			sb.append(", \"userId\":\"").append(userId).append("\"");
		}
		if (commId != null) {
			sb.append(", \"commId\":\"").append(commId).append("\"");
		}
		return sb.toString();
	}
}