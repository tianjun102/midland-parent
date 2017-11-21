package com.midland.web.model;


import java.util.List;

public class Questions{
	/**
	 * 问题主键
	 **/
	private Integer id;
	/**
	 * 提问主题
	 **/
	private String questionsTitle;
	/**
	 * '0=网站；1=微站'
	 **/
	private Integer source;
	/**
	 * 提问时间
	 **/
	private String questionTime;
	/**
	 * 
	 **/
	private String questionName;
	/**
	 * 提问手机
	 **/
	private String questionPhone;
	/**
	 * 审核人
	 **/
	private String auditor;
	/**
	 * 提问类容
	 **/
	private String questionsArea;
	/**
	 * 0=待审核；1=审核通过；2=审核失败
	 **/
	private Integer status;
	/**
	 * 点击量
	 **/
	private Integer clickNum;
	/**
	 * 点赞
	 **/
	private Integer fabulous;
	/**
	 * 审核备注
	 **/
	private String auditRemark;
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

	private String startTime;

	private String endTime;

	/**
	 * 按照字段降序排
	 */
	private String descName;
	/**
	 * 提问人id
	 */
	public Integer userId;
	/**
	 * 提问人名称
	 */
	public String userName;
	/**
	 * 被关注的问题id集合
	 */
	public List<Integer> attentionList;

	private String headImg;
	/**
	 * 回答次数
	 */
	private Integer answerNum;


	public Integer getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	public List<Integer> getAttentionList() {
		return attentionList;
	}

	public void setAttentionList(List<Integer> attentionList) {
		this.attentionList = attentionList;
	}

	public Integer getQuestionAuthorId() {
		return userId;
	}

	public void setQuestionAuthorId(Integer userId) {
		this.userId = userId;
	}

	public String getQuestionAuthorName() {
		return userName;
	}

	public void setQuestionAuthorName(String userName) {
		this.userName = userName;
	}

	public String getDescName() {
		return descName;
	}
	public void setDescName(String descName) {
		this.descName = descName;
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

	public String getQuestionsTitle() {
		return questionsTitle;
	}

	public void setQuestionsTitle(String questionsTitle) {
		this.questionsTitle = questionsTitle;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getQuestionTime() {
		return questionTime;
	}

	public void setQuestionTime(String questionTime) {
		this.questionTime = questionTime;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionPhone() {
		return questionPhone;
	}

	public void setQuestionPhone(String questionPhone) {
		this.questionPhone = questionPhone;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getQuestionsArea() {
		return questionsArea;
	}

	public void setQuestionsArea(String questionsArea) {
		this.questionsArea = questionsArea;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getFabulous() {
		return fabulous;
	}

	public void setFabulous(Integer fabulous) {
		this.fabulous = fabulous;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Questions{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (questionsTitle != null) {
			sb.append(", \"questionsTitle\":\"").append(questionsTitle).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		if (questionTime != null) {
			sb.append(", \"questionTime\":\"").append(questionTime).append("\"");
		}
		if (questionName != null) {
			sb.append(", \"questionName\":\"").append(questionName).append("\"");
		}
		if (questionPhone != null) {
			sb.append(", \"questionPhone\":\"").append(questionPhone).append("\"");
		}
		if (auditor != null) {
			sb.append(", \"auditor\":\"").append(auditor).append("\"");
		}
		if (questionsArea != null) {
			sb.append(", \"questionsArea\":\"").append(questionsArea).append("\"");
		}
		if (status != null) {
			sb.append(", \"status\":\"").append(status).append("\"");
		}
		if (clickNum != null) {
			sb.append(", \"clickNum\":\"").append(clickNum).append("\"");
		}
		if (fabulous != null) {
			sb.append(", \"fabulous\":\"").append(fabulous).append("\"");
		}
		if (auditRemark != null) {
			sb.append(", \"auditRemark\":\"").append(auditRemark).append("\"");
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
		if (answerNum != null) {
			sb.append(", \"answerNum\":").append(answerNum);
		}
		sb.append("}");
		return sb.toString();
	}
}