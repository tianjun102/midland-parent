package com.midland.web.model;


public class Comment{
	/**
	 * 资讯评论表id
	 **/
	private Integer id;
	/**
	 * 评论
	 **/
	private String comment;
	/**
	 * 用户名
	 **/
	private String user;
	/**
	 * 0=审核通过；1=审核拒绝
	 **/
	private Integer status;
	/**
	 * 0=网站；1=微站
	 **/
	private Integer source;
	/**
	 * 0=正常；1=删除
	 **/
	private Integer isDelete;
	/**
	 * 关联资讯表id
	 **/
	private Integer informationId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getInformationId() {
		return informationId;
	}

	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Comment{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (comment != null) {
			sb.append(", \"comment\":\"").append(comment).append("\"");
		}
		if (user != null) {
			sb.append(", \"user\":\"").append(user).append("\"");
		}
		if (status != null) {
			sb.append(", \"status\":\"").append(status).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (informationId != null) {
			sb.append(", \"informationId\":\"").append(informationId).append("\"");
		}
		return sb.toString();
	}
}