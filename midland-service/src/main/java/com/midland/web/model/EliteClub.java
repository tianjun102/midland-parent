package com.midland.web.model;

public class EliteClub {
    /**
     * 精英会主键id
     **/
    private Integer id;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 上传图片路径
     **/
    private String imgUrl;
    /**
     * 图片描述
     **/
    private String imgDesc;
    /**
     * 活动名称
     **/
    private String adName;
    /**
     * 活动时间
     **/
    private String adTime;
    /**
     * 活动主题
     **/
    private String adTitle;
    /**
     * 活动地址
     **/
    private String adAddress;
    /**
     * 活动详情
     **/
    private String detail;
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 0显示；1隐藏
     **/
    private Integer isShow;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdTime() {
        return adTime;
    }

    public void setAdTime(String adTime) {
        this.adTime = adTime;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdAddress() {
        return adAddress;
    }

    public void setAdAddress(String adAddress) {
        this.adAddress = adAddress;
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

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EliteClub{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (imgUrl != null) {
            sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
        }
        if (imgDesc != null) {
            sb.append(", \"imgDesc\":\"").append(imgDesc).append("\"");
        }
        if (adName != null) {
            sb.append(", \"adName\":\"").append(adName).append("\"");
        }
        if (adTime != null) {
            sb.append(", \"adTime\":\"").append(adTime).append("\"");
        }
        if (adTitle != null) {
            sb.append(", \"adTitle\":\"").append(adTitle).append("\"");
        }
        if (adAddress != null) {
            sb.append(", \"adAddress\":\"").append(adAddress).append("\"");
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
        if (isShow != null) {
            sb.append(", \"isShow\":\"").append(isShow).append("\"");
        }
        return sb.toString();
    }
}