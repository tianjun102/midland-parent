package com.midland.web.model;

/**
 * 联络邮箱管理
 */
public class LiaisonRecordEmail {
    /**
     *
     **/
    private Integer id;
    /**
     * 分类：0楼盘资料，1刊登广告，2精英招聘，3客户服务，4其它
     **/
    private Integer category;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 手机号码
     **/
    private String phone;
    /**
     * 联系人id
     **/
    private String contactId;
    /**
     * 是否删除：0否，1是
     **/
    private Integer isDelete;
    /**
     * 联系人名称
     **/
    private String contactName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LiaisonRecordEmail{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (category != null) {
            sb.append(", \"category\":\"").append(category).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (email != null) {
            sb.append(", \"email\":\"").append(email).append("\"");
        }
        if (phone != null) {
            sb.append(", \"phone\":\"").append(phone).append("\"");
        }
        if (contactId != null) {
            sb.append(", \"contactId\":\"").append(contactId).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (contactName != null) {
            sb.append(", \"contactName\":\"").append(contactName).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }
}