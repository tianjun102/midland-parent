package com.midland.web.model;

/**
 * 回答
 */
public class Answer {
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
     * 回答人名字（也可能是经纪人名称）
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
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;
    /**
     * 审核状态：0未审核，1审核通过，2审核不通过
     **/
    private Integer auditStatus;
    /**
     * 回答人电话（经纪人电话）
     **/
    private String answerPhone;
    /**
     * 回答人id（也可能是经纪人工号）
     **/
    private Integer answerNo;

    private String headImg;


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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAnswerPhone() {
        return answerPhone;
    }

    public void setAnswerPhone(String answerPhone) {
        this.answerPhone = answerPhone;
    }

    public Integer getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(Integer answerNo) {
        this.answerNo = answerNo;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Answer{");
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
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (auditStatus != null) {
            sb.append(", \"auditStatus\":\"").append(auditStatus).append("\"");
        }
        if (answerPhone != null) {
            sb.append(", \"answerPhone\":\"").append(answerPhone).append("\"");
        }
        if (answerNo != null) {
            sb.append(", \"answerNo\":\"").append(answerNo).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }
}