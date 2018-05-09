package com.midland.web.model;

/**
 * 轮播图
 */
public class Banner {
    /**
     *
     **/
    private Integer id;
    /**
     * PCbanner图地址
     **/
    private String bannerImg;
    /**
     * 图片链接
     **/
    private String bannerLinkurl;
    /**
     * 图片说明   alt
     **/
    private String imgDesc;
    /**
     * 排序，默认升序
     **/
    private Integer sortOrder;
    /**
     * 是否启用：0=未启用；1=启用；
     **/
    private Integer enabled;
    /**
     *
     **/
    private String cityId;
    /**
     * 0=网站；1=微站
     **/
    private Integer source;
    /**
     * 模块；0=首页；1=资讯；2调研市场
     **/
    private String model;
    /**
     * 开始时间
     **/
    private String startTime;
    /**
     * 结束时间
     **/
    private String endTime;
    /**
     * banner位置
     **/
    private String position;
    /**
     * 视频链接
     **/
    private String videoUrl;
    /**
     * 详情
     **/
    private String detail;
    /**
     * 1=删除；0=正常
     **/
    private Integer isDelete;
    /**
     * 城市名称
     *
     * @return
     */
    private String cityName;

    /**
     * 点击量
     */
    private Integer clikNum;

    private Integer type;

    private Integer orderBy;

    private Integer nofollow;

    public Integer getNofollow() {
        return nofollow;
    }

    public void setNofollow(Integer nofollow) {
        this.nofollow = nofollow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getBannerLinkurl() {
        return bannerLinkurl;
    }

    public void setBannerLinkurl(String bannerLinkurl) {
        this.bannerLinkurl = bannerLinkurl;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getClikNum() {
        return clikNum;
    }

    public void setClikNum(Integer clikNum) {
        this.clikNum = clikNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Banner{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (bannerImg != null) {
            sb.append(", \"bannerImg\":\"").append(bannerImg).append("\"");
        }
        if (bannerLinkurl != null) {
            sb.append(", \"bannerLinkurl\":\"").append(bannerLinkurl).append("\"");
        }
        if (imgDesc != null) {
            sb.append(", \"imgDesc\":\"").append(imgDesc).append("\"");
        }
        if (sortOrder != null) {
            sb.append(", \"sortOrder\":\"").append(sortOrder).append("\"");
        }
        if (enabled != null) {
            sb.append(", \"enabled\":\"").append(enabled).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (source != null) {
            sb.append(", \"source\":\"").append(source).append("\"");
        }
        if (model != null) {
            sb.append(", \"model\":\"").append(model).append("\"");
        }
        if (startTime != null) {
            sb.append(", \"startTime\":\"").append(startTime).append("\"");
        }
        if (endTime != null) {
            sb.append(", \"endTime\":\"").append(endTime).append("\"");
        }
        if (position != null) {
            sb.append(", \"position\":\"").append(position).append("\"");
        }
        if (videoUrl != null) {
            sb.append(", \"videoUrl\":\"").append(videoUrl).append("\"");
        }
        if (detail != null) {
            sb.append(", \"detail\":\"").append(detail).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (clikNum != null) {
            sb.append(", \"clikNum\":\"").append(clikNum).append("\"");
        }
        return sb.toString();
    }
}