package com.midland.web.model;

/**
 * 精英会vip
 */
public class EliteVip {
    /**
     *
     **/
    private Integer id;
    /**
     * 精英类别
     **/
    private String type;
    /**
     * 会员级别
     **/
    private String level;
    /**
     * 中文名称
     **/
    private String cname;
    /**
     * 英文名称
     **/
    private String ename;
    /**
     * 所属地区
     **/
    private String address;
    /**
     * 职务
     **/
    private String post;
    /**
     * 图片路径
     **/
    private String imgUrl;
    /**
     * 图片描述
     **/
    private String imgDesc;
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;
    /**
     * 分类id
     **/
    private Integer cateId;
    /**
     * 分类名称
     **/
    private String cateName;
    /**
     * 城市id
     **/
    private Integer cityId;
    /**
     * 城市名称
     **/
    private String cityName;


    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EliteVip{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (type != null) {
            sb.append(", \"type\":\"").append(type).append("\"");
        }
        if (level != null) {
            sb.append(", \"level\":\"").append(level).append("\"");
        }
        if (cname != null) {
            sb.append(", \"cname\":\"").append(cname).append("\"");
        }
        if (ename != null) {
            sb.append(", \"ename\":\"").append(ename).append("\"");
        }
        if (address != null) {
            sb.append(", \"address\":\"").append(address).append("\"");
        }
        if (post != null) {
            sb.append(", \"post\":\"").append(post).append("\"");
        }
        if (imgUrl != null) {
            sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
        }
        if (imgDesc != null) {
            sb.append(", \"imgDesc\":\"").append(imgDesc).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (cateId != null) {
            sb.append(", \"cateId\":\"").append(cateId).append("\"");
        }
        if (cateName != null) {
            sb.append(", \"cateName\":\"").append(cateName).append("\"");
        }
        return sb.toString();
    }
}