package com.midland.web.model;


public class LoanType {
    /**
     * 贷款类型表
     **/
    private Integer id;
    /**
     * 贷款种类
     **/
    private String name;
    /**
     * 利率
     **/
    private String rate;
    /**
     * 说明
     **/
    private String detail;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 排序字段
     **/
    private Integer orderBy;
    /**
     * 0=正常；1删除
     **/
    private Integer isDelete;
    /**
     * 0=显示；1=隐藏
     **/
    private Integer isShow;
    /**
     * 使用说明
     **/
    private String description;


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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoanType{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (name != null) {
            sb.append(", \"name\":\"").append(name).append("\"");
        }
        if (rate != null) {
            sb.append(", \"rate\":\"").append(rate).append("\"");
        }
        if (detail != null) {
            sb.append(", \"detail\":\"").append(detail).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (orderBy != null) {
            sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (isShow != null) {
            sb.append(", \"isShow\":\"").append(isShow).append("\"");
        }
        if (description != null) {
            sb.append(", \"description\":\"").append(description).append("\"");
        }
        return sb.toString();
    }
}