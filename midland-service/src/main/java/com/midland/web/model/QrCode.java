package com.midland.web.model;

/**
 * 二维码
 */
public class QrCode {
    /**
     * id 二维码管理表
     **/
    private Integer id;
    /**
     * 二维码连接
     **/
    private String imgUrl;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 平台
     **/
    private Integer source;
    /**
     * 名称
     **/
    private String name;
    /**
     * 是否开放
     **/
    private Integer isShow;
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 设置底部版权信息、备案号等
     **/
    private String detail;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QrCode{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (imgUrl != null) {
            sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (source != null) {
            sb.append(", \"source\":\"").append(source).append("\"");
        }
        if (name != null) {
            sb.append(", \"name\":\"").append(name).append("\"");
        }
        if (isShow != null) {
            sb.append(", \"isShow\":\"").append(isShow).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (detail != null) {
            sb.append(", \"detail\":\"").append(detail).append("\"");
        }
        return sb.toString();
    }
}