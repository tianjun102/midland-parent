package com.midland.web.model;


public class LayoutMap {
    /**
     * 户型分布图
     **/
    private Integer id;
    /**
     * 图片url
     **/
    private String imgUrl;
    /**
     * 图片类型；1=一居室2=二居室3=三居室
     **/
    private String type;
    /**
     * 标题
     **/
    private String title;
    /**
     * 房屋朝向,0东，1东南，2南，3西南，4西，5西北，6北，7东北
     **/
    private Integer turned;
    /**
     * 面积
     **/
    private String acreage;
    /**
     * 均价
     **/
    private Double avgPrice;
    /**
     * 在售套数
     **/
    private Integer saleingNum;
    /**
     * 0=正常；1=删除
     **/
    private Integer isDelete;
    /**
     * 0=正常；1=隐藏
     **/
    private Integer isShow;
    /**
     * 排序字段
     **/
    private Integer orderBy;
    /**
     * 计算价格
     **/
    private String price;
    /**
     * hot_hand表的id
     **/
    private Integer hotHandId;
    /**
     * 创建时间
     **/
    private String createTime;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTurned() {
        return turned;
    }

    public void setTurned(Integer turned) {
        this.turned = turned;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Integer getSaleingNum() {
        return saleingNum;
    }

    public void setSaleingNum(Integer saleingNum) {
        this.saleingNum = saleingNum;
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

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getHotHandId() {
        return hotHandId;
    }

    public void setHotHandId(Integer hotHandId) {
        this.hotHandId = hotHandId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LayoutMap{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (imgUrl != null) {
            sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
        }
        if (type != null) {
            sb.append(", \"type\":\"").append(type).append("\"");
        }
        if (title != null) {
            sb.append(", \"title\":\"").append(title).append("\"");
        }
        if (turned != null) {
            sb.append(", \"turned\":\"").append(turned).append("\"");
        }
        if (acreage != null) {
            sb.append(", \"acreage\":\"").append(acreage).append("\"");
        }
        if (avgPrice != null) {
            sb.append(", \"avgPrice\":\"").append(avgPrice).append("\"");
        }
        if (saleingNum != null) {
            sb.append(", \"saleingNum\":\"").append(saleingNum).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (isShow != null) {
            sb.append(", \"isShow\":\"").append(isShow).append("\"");
        }
        if (orderBy != null) {
            sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
        }
        if (price != null) {
            sb.append(", \"price\":\"").append(price).append("\"");
        }
        if (hotHandId != null) {
            sb.append(", \"hotHandId\":\"").append(hotHandId).append("\"");
        }
        if (createTime != null) {
            sb.append(", \"createTime\":\"").append(createTime).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }
}