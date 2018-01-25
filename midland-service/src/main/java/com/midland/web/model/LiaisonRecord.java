package com.midland.web.model;

public class LiaisonRecord {
    /**
     * 联络记录表id
     **/
    private Integer id;
    /**
     * 留言者名称
     **/
    private String name;
    /**
     * 手机
     **/
    private String phone;
    /**
     * 分类
     **/
    private String category;
    /**
     * 留言
     **/
    private String leavingMessage;
    /**
     * 提交时间
     **/
    private String addTime;
    /**
     * 是否联系:0未联系，1已联系
     **/
    private Integer isOntact;
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;
    /**
     * 留言者id
     **/
    private Integer userId;
    /**
     * 邮箱
     **/
    private String email;
    private String startTime;
    private String endTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLeavingMessage() {
        return leavingMessage;
    }

    public void setLeavingMessage(String leavingMessage) {
        this.leavingMessage = leavingMessage;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getIsOntact() {
        return isOntact;
    }

    public void setIsOntact(Integer isOntact) {
        this.isOntact = isOntact;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LiaisonRecord{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (name != null) {
            sb.append(", \"name\":\"").append(name).append("\"");
        }
        if (phone != null) {
            sb.append(", \"phone\":\"").append(phone).append("\"");
        }
        if (category != null) {
            sb.append(", \"category\":\"").append(category).append("\"");
        }
        if (leavingMessage != null) {
            sb.append(", \"leavingMessage\":\"").append(leavingMessage).append("\"");
        }
        if (addTime != null) {
            sb.append(", \"addTime\":\"").append(addTime).append("\"");
        }
        if (isOntact != null) {
            sb.append(", \"isOntact\":\"").append(isOntact).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (userId != null) {
            sb.append(", \"userId\":\"").append(userId).append("\"");
        }
        if (email != null) {
            sb.append(", \"email\":\"").append(email).append("\"");
        }
        return sb.toString();
    }
}