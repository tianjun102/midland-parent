package com.midland.web.model;

public class Complaints {
    /**
     * 投诉意见表
     **/
    private Integer id;
    /**
     * 微信名称
     **/
    private String wxName;
    /**
     * 微信名称
     **/
    private String wxNum;
    /**
     * open_id
     **/
    private String openId;
    /**
     * 详细类容
     **/
    private String detail;
    /**
     *
     **/
    private String addTime;
    /**
     * 1=伊莱特；2饭煲事业部；3精品事业部；4料理事业部；5配件事业部
     **/
    private Integer department;
    /**
     * 1=投诉；2=建议
     **/
    private Integer type;
    /**
     * 是否删除
     **/
    private Integer isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getWxNum() {
        return wxNum;
    }

    public void setWxNum(String wxNum) {
        this.wxNum = wxNum;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Complaints{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (wxName != null) {
            sb.append(", \"wxName\":\"").append(wxName).append("\"");
        }
        if (wxNum != null) {
            sb.append(", \"wxNum\":\"").append(wxNum).append("\"");
        }
        if (openId != null) {
            sb.append(", \"openId\":\"").append(openId).append("\"");
        }
        if (detail != null) {
            sb.append(", \"detail\":\"").append(detail).append("\"");
        }
        if (addTime != null) {
            sb.append(", \"addTime\":\"").append(addTime).append("\"");
        }
        if (department != null) {
            sb.append(", \"department\":\"").append(department).append("\"");
        }
        if (type != null) {
            sb.append(", \"type\":\"").append(type).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        return sb.toString();
    }
}