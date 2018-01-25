package com.midland.web.model;


public class HotSearch {
    /**
     *
     **/
    private Integer id;
    /**
     * 关键字
     **/
    private String keywords;
    /**
     * 查询次数
     **/
    private Integer count;
    /**
     * 排序，默认升序
     **/
    private Integer sortOrder;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 菜单id
     **/
    private Integer menuId;
    /**
     * 是否开启
     **/
    private Integer isShow;
    /**
     * 排序
     **/
    private Integer orderBy;
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 菜单名称
     **/
    private String menuName;

    /**
     * 0网站,1微站
     */
    private Integer source;

    /**
     * 链接
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HotSearch{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (keywords != null) {
            sb.append(", \"keywords\":\"").append(keywords).append("\"");
        }
        if (count != null) {
            sb.append(", \"count\":\"").append(count).append("\"");
        }
        if (sortOrder != null) {
            sb.append(", \"sortOrder\":\"").append(sortOrder).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (menuId != null) {
            sb.append(", \"menuId\":\"").append(menuId).append("\"");
        }
        if (isShow != null) {
            sb.append(", \"isShow\":\"").append(isShow).append("\"");
        }
        if (orderBy != null) {
            sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (menuName != null) {
            sb.append(", \"menuName\":\"").append(menuName).append("\"");
        }
        return sb.toString();
    }
}