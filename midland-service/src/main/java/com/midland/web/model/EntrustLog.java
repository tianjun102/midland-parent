package com.midland.web.model;

public class EntrustLog {
    /**
     *
     **/
    private Integer entrustLogId;
    /**
     * 记录时间
     **/
    private String logTime;
    /**
     * 操作人id
     **/
    private String operatorId;
    /**
     * 操作人名称
     **/
    private String operatorName;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 状态 ： 0预约生成，1经纪人重新分配，2已联系
     **/
    private Integer state;
    /**
     * 委拖记录id
     **/
    private Integer entrustId;
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;


    public Integer getEntrustLogId() {
        return entrustLogId;
    }

    public void setEntrustLogId(Integer entrustLogId) {
        this.entrustLogId = entrustLogId;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EntrustLog{");
        if (entrustLogId != null) {
            sb.append(", \"entrustLogId\":\"").append(entrustLogId).append("\"");
        }
        if (logTime != null) {
            sb.append(", \"logTime\":\"").append(logTime).append("\"");
        }
        if (operatorId != null) {
            sb.append(", \"operatorId\":\"").append(operatorId).append("\"");
        }
        if (operatorName != null) {
            sb.append(", \"operatorName\":\"").append(operatorName).append("\"");
        }
        if (remark != null) {
            sb.append(", \"remark\":\"").append(remark).append("\"");
        }
        if (state != null) {
            sb.append(", \"state\":\"").append(state).append("\"");
        }
        if (entrustId != null) {
            sb.append(", \"entrustId\":\"").append(entrustId).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        return sb.toString();
    }
}