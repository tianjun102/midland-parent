package com.midland.web.model;


public class Attention{
	/**
	 * 
	 **/
	private Integer id;
	/**
	 * 关注的用户id
	 **/
	private Integer userId;
	/**
	 * 关注的类型
	 **/
	private Integer type;
	/**
	 * 被关注对象的id，如被关注的问题id
	 **/
	private Integer otherId;
	/**
	 * 关注时间
	 **/
	private String createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWebUserId() {
		return userId;
	}

	public void setWebUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOtherId() {
		return otherId;
	}

	public void setOtherId(Integer otherId) {
		this.otherId = otherId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Attention{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (userId != null) {
			sb.append(", \"userId\":\"").append(userId).append("\"");
		}
		if (type != null) {
			sb.append(", \"type\":\"").append(type).append("\"");
		}
		if (otherId != null) {
			sb.append(", \"otherId\":\"").append(otherId).append("\"");
		}
		if (createTime != null) {
			sb.append(", \"createTime\":\"").append(createTime).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}