package com.midland.web.model;


import java.util.List;

/**
 * 分类
 */
public class Category {
    /**
     *
     **/
    private Integer id;
    /**
     * 分类名称
     **/
    private String cateName;
    /**
     * 0=开放；1=关闭
     **/
    private Integer status;
    /**
     * 排序值
     **/
    private Integer orderBy;
    /**
     * 父节点id
     **/
    private Integer parentId;
    /**
     * type=0 市场研究
     * type=1 资讯
     * type=2 会员分类
     * type=3 热门关注分类
     * type=4 网站地图分类
     *
     **/
    private Integer type;
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 城市ID
     **/
    private String cityId;
    /**
     * 分类描述
     **/
    private String cateDesc;

    private Integer chirdCount;   //子类数

    private String parentName;

    private Integer isShow;

    private Integer source;

    private String linkUrl;

    private List<Category> parents;

    /**
     * 模块id
     * 0   首页
     * 1   新房
     * 2   二手房
     * 3   租房
     * 4   写字楼
     * 5   商铺
     * 6   小区
     * 7   经纪人
     * 8   外销网
     * 9   市场调究
     * 10  资讯
     * 11  问答
     */
    private Integer modeId;
    /**
     * 模块名称
     * 0   首页
     * 1   新房
     * 2   二手房
     * 3   租房
     * 4   写字楼
     * 5   商铺
     * 6   小区
     * 7   经纪人
     * 8   外销网
     * 9   市场调究
     * 10  资讯
     * 11  问答
     */
    private String modeName;

    /**
     * 是否是超链接0是,1否
     */
    private String isHref;

    /**
     * 写字楼的租售:
     * 0 租
     * 1 售
     */
    private String sellRent;

    public String getSellRent() {
        return sellRent;
    }

    public void setSellRent(String sellRent) {
        this.sellRent = sellRent;
    }

    public String getIsHref() {
        return isHref;
    }

    public void setIsHref(String isHref) {
        this.isHref = isHref;
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCateDesc() {
        return cateDesc;
    }

    public void setCateDesc(String cateDesc) {
        this.cateDesc = cateDesc;
    }

    public Integer getChirdCount() {
        return chirdCount;
    }

    public void setChirdCount(Integer chirdCount) {
        this.chirdCount = chirdCount;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }


    public List<Category> getParents() {
        return parents;
    }

    public void setParents(List<Category> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Category{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (cateName != null) {
            sb.append(", \"cateName\":\"").append(cateName).append("\"");
        }
        if (status != null) {
            sb.append(", \"status\":\"").append(status).append("\"");
        }
        if (orderBy != null) {
            sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
        }
        if (parentId != null) {
            sb.append(", \"parentId\":\"").append(parentId).append("\"");
        }
        if (type != null) {
            sb.append(", \"type\":\"").append(type).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (cateDesc != null) {
            sb.append(", \"cateDesc\":\"").append(cateDesc).append("\"");
        }

        return sb.toString();
    }
}