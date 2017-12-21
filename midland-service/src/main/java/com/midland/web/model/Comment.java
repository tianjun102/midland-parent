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
	/**
	 * 0=资讯(点评文章)；1=委托（点评经纪人）；2=预约（点评经纪人）
	 **/
	private Integer type;

	private String commentTime;

	private String startTime;

	private String endTime;
	/**
	 * 点赞数
	 **/
	private Integer like;

	private String userId;

	private String headImg;

	private String orderByPopularity;

	private String orderByTime;

	private Integer replyNum;
	private String informationTitle;
	private String informationImgUrl;
	private Integer allScore;
	private Integer serviceScore;
	private Integer professionalSkills;
	private Integer marketSpecialty;
	private String agentName;
	private String agentUrl;
	private String agentId;


	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Integer getAllScore() {
		return allScore;
	}

	public void setAllScore(Integer allScore) {
		this.allScore = allScore;
	}

	public Integer getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(Integer serviceScore) {
		this.serviceScore = serviceScore;
	}

	public Integer getProfessionalSkills() {
		return professionalSkills;
	}

	public void setProfessionalSkills(Integer professionalSkills) {
		this.professionalSkills = professionalSkills;
	}

	public Integer getMarketSpecialty() {
		return marketSpecialty;
	}

	public void setMarketSpecialty(Integer marketSpecialty) {
		this.marketSpecialty = marketSpecialty;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentUrl() {
		return agentUrl;
	}

	public void setAgentUrl(String agentUrl) {
		this.agentUrl = agentUrl;
	}

	public String getInformationTitle() {
		return informationTitle;
	}

	public void setInformationTitle(String informationTitle) {
		this.informationTitle = informationTitle;
	}

	public String getInformationImgUrl() {
		return informationImgUrl;
	}

	public void setInformationImgUrl(String informationImgUrl) {
		this.informationImgUrl = informationImgUrl;
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getOrderByPopularity() {
		return orderByPopularity;
	}

	public void setOrderByPopularity(String orderByPopularity) {
		this.orderByPopularity = orderByPopularity;
	}

	public String getOrderByTime() {
		return orderByTime;
	}

	public void setOrderByTime(String orderByTime) {
		this.orderByTime = orderByTime;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
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