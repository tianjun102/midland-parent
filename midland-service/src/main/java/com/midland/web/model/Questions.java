package com.midland.web.model;

public class Questions {
    private Integer id;
    
    /**
     * 问题标题
     */
    private String questionsTitle;
    /**
     * 平台
     */
    private Integer source;
    /**
     * 提问时间
     */
    private String questionTime;
    /**
     * 提问人昵称
     */
    private String questionName;
    /**
     * 提问人手机号
     */
    private String questionPhone;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 点击数
     */
    private Integer clickNum;
    /**
     *
     */
    private Integer fabulous;
    /**
     * 提问内容
     */
    private String questionsArea;
    private String startTime;
    private String endTime;
    
    private String auditRemark;

    private Integer isDelete;
    
    public String getAuditRemark() {
        return auditRemark;
    }
    
    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
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
        this.questionsTitle = questionsTitle == null ? null : questionsTitle.trim();
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
        this.questionName = questionName == null ? null : questionName.trim();
    }

    public String getQuestionPhone() {
        return questionPhone;
    }

    public void setQuestionPhone(String questionPhone) {
        this.questionPhone = questionPhone == null ? null : questionPhone.trim();
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
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

    public String getQuestionsArea() {
        return questionsArea;
    }

    public void setQuestionsArea(String questionsArea) {
        this.questionsArea = questionsArea == null ? null : questionsArea.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}