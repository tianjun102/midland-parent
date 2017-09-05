package com.midland.web.model;

public class Answer{
	/**
	 * 回答表主键ID
	 **/
	private Integer id;
	/**
	 * 回答类容
	 **/
	private String answerArea;
	/**
	 * 回答时间
	 **/
	private String answerTime;
	/**
	 * 回答人名字
	 **/
	private String answerName;
	/**
	 * 反对总数
	 **/
	private Integer againstNum;
	/**
	 * 支持总数
	 **/
	private Integer supportNum;
	/**
	 * 提问id 关联提问表id
	 **/
	private Integer questionsId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswerArea() {
		return answerArea;
	}

	public void setAnswerArea(String answerArea) {
		this.answerArea = answerArea;
	}

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	public Integer getAgainstNum() {
		return againstNum;
	}

	public void setAgainstNum(Integer againstNum) {
		this.againstNum = againstNum;
	}

	public Integer getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(Integer supportNum) {
		this.supportNum = supportNum;
	}

	public Integer getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(Integer questionsId) {
		this.questionsId = questionsId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Answer{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (answerArea != null) {
			sb.append(", \"answerArea\":\"").append(answerArea).append("\"");
		}
		if (answerTime != null) {
			sb.append(", \"answerTime\":\"").append(answerTime).append("\"");
		}
		if (answerName != null) {
			sb.append(", \"answerName\":\"").append(answerName).append("\"");
		}
		if (againstNum != null) {
			sb.append(", \"againstNum\":\"").append(againstNum).append("\"");
		}
		if (supportNum != null) {
			sb.append(", \"supportNum\":\"").append(supportNum).append("\"");
		}
		if (questionsId != null) {
			sb.append(", \"questionsId\":\"").append(questionsId).append("\"");
		}
		return sb.toString();
	}
}