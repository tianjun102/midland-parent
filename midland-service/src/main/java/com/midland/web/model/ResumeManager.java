package com.midland.web.model;

/**
 * 简历投递
 */
public class ResumeManager {
    /**
     * 简历管理主键id
     **/
    private Integer id;
    /**
     * 投简历人名
     **/
    private String deliverer;
    /**
     * 联系电话
     **/
    private String phone;
    /**
     * 岗位
     **/
    private String post;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 投递时间
     **/
    private String addTime;
    /**
     * 附件地址
     **/
    private String enclosureUrl;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 是否删除0=正常；1=删除
     **/
    private Integer isDelete;
    /**
     * 回复
     **/
    private String reply;

    private String startTime;

    private String endTime;

    private String title;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String enclosureUrl) {
        this.enclosureUrl = enclosureUrl;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResumeManager{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (deliverer != null) {
            sb.append(", \"deliverer\":\"").append(deliverer).append("\"");
        }
        if (phone != null) {
            sb.append(", \"phone\":\"").append(phone).append("\"");
        }
        if (post != null) {
            sb.append(", \"post\":\"").append(post).append("\"");
        }
        if (email != null) {
            sb.append(", \"email\":\"").append(email).append("\"");
        }
        if (addTime != null) {
            sb.append(", \"addTime\":\"").append(addTime).append("\"");
        }
        if (enclosureUrl != null) {
            sb.append(", \"enclosureUrl\":\"").append(enclosureUrl).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (reply != null) {
            sb.append(", \"reply\":\"").append(reply).append("\"");
        }
        return sb.toString();
    }
}