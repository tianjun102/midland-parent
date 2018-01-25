package com.midland.web.model;


public class SpecialPage {
    /**
     * 特殊页面配置表；id主键
     **/
    private Integer id;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 0=网站；1=微站
     **/
    private Integer source;
    /**
     * 模块名称
     **/
    private String modeName;
    /**
     * 位置
     **/
    private Integer position;
    /**
     * 标题
     **/
    private String title;
    /**
     * 描述
     **/
    private String description;
    /**
     * 价格
     **/
    private Double price;
    /**
     * 地址
     **/
    private String address;
    /**
     * 图片连接
     **/
    private String imgUrl;
    /**
     * 地铁距离描述
     **/
    private String subwayDistance;
    /**
     * 外网连接
     **/
    private String linkUrl;
    /**
     * 详情
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
     * 缩略图描述
     **/
    private String imgDesc;
    /**
     * 是否显示隐藏0=显示；1隐藏
     **/
    private Integer isShow;

    private String detailsUrl;

    private Integer orderBy;


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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSubwayDistance() {
        return subwayDistance;
    }

    public void setSubwayDistance(String subwayDistance) {
        this.subwayDistance = subwayDistance;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
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

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }


    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SpecialPage{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (source != null) {
            sb.append(", \"source\":\"").append(source).append("\"");
        }
        if (modeName != null) {
            sb.append(", \"modeName\":\"").append(modeName).append("\"");
        }
        if (position != null) {
            sb.append(", \"position\":\"").append(position).append("\"");
        }
        if (title != null) {
            sb.append(", \"title\":\"").append(title).append("\"");
        }
        if (description != null) {
            sb.append(", \"description\":\"").append(description).append("\"");
        }
        if (price != null) {
            sb.append(", \"price\":\"").append(price).append("\"");
        }
        if (address != null) {
            sb.append(", \"address\":\"").append(address).append("\"");
        }
        if (imgUrl != null) {
            sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
        }
        if (subwayDistance != null) {
            sb.append(", \"subwayDistance\":\"").append(subwayDistance).append("\"");
        }
        if (linkUrl != null) {
            sb.append(", \"linkUrl\":\"").append(linkUrl).append("\"");
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
        if (imgDesc != null) {
            sb.append(", \"imgDesc\":\"").append(imgDesc).append("\"");
        }
        return sb.toString();
    }
}