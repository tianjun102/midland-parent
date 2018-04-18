package com.midland.web.model;

/**
 * 菜单
 */
public class Menu {
    /**
     * 菜单表主键
     **/
    private Integer id;
    /**
     * 小图标
     **/
    private String iconImg;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 菜单名称
     **/
    private String menuName;
    /**
     * 点击量
     **/
    private Integer clickNum;
    /**
     * 排序字段
     **/
    private Integer orderBy;
    /**
     * 菜单栏链接
     **/
    private String url;
    /**
     * 是否开启 0隐藏，1开启
     **/
    private Integer isShow;
    /**
     * position=1顶部；position=0底部
     **/
    private Integer position;
    /**
     * 父节点id
     **/
    private Integer parentId;
    /**
     * 平台 0=网站；1=微站
     **/
    private Integer source;
    /**
     * 0未删除，1删除
     **/
    private Integer isDelete;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 省id
     **/
    private String provinceId;
    /**
     * 省名称
     **/
    private String provinceName;
    /**
     * 微站菜单类型Id
     */
    private Integer menuTypeId;
    /**
     * 微站菜单类型名称
     */
    private String menuTypeName;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getMenuTypeId() {
        return menuTypeId;
    }

    public void setMenuTypeId(Integer menuTypeId) {
        this.menuTypeId = menuTypeId;
    }

    public String getMenuTypeName() {
        return menuTypeName;
    }

    public void setMenuTypeName(String menuTypeName) {
        this.menuTypeName = menuTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Menu{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (iconImg != null) {
            sb.append(", \"iconImg\":\"").append(iconImg).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (menuName != null) {
            sb.append(", \"menuName\":\"").append(menuName).append("\"");
        }
        if (clickNum != null) {
            sb.append(", \"clickNum\":\"").append(clickNum).append("\"");
        }
        if (orderBy != null) {
            sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
        }
        if (url != null) {
            sb.append(", \"url\":\"").append(url).append("\"");
        }
        if (isShow != null) {
            sb.append(", \"isShow\":\"").append(isShow).append("\"");
        }
        if (position != null) {
            sb.append(", \"position\":\"").append(position).append("\"");
        }
        if (parentId != null) {
            sb.append(", \"parentId\":\"").append(parentId).append("\"");
        }
        if (source != null) {
            sb.append(", \"source\":\"").append(source).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        return sb.toString();
    }
}