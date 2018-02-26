package com.midland.web.model;

/**
 * 热门关注
 */
public class Popular {
    /**
     * 热门关注配置表id 主键
     **/
    private Integer id;
    /**
     * 分类表id
     **/
    private Integer cateId;
    /**
     * 模块菜单id
     **/
    private Integer menuId;
    /**
     * 热门关注名称
     **/
    private String name;
    /**
     * 平台 1=网站；2=微站
     **/
    private Integer source;
    /**
     * url链接
     **/
    private String url;
    /**
     * 省份id
     **/
    private String provinceId;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 区域id
     **/
    private String areaId;
    /**
     * 片区id
     **/
    private String sheetId;
    /**
     * 1=删除；0=正常
     **/
    private Integer isDelete;
    /**
     * 省份名称
     **/
    private String provinceName;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 区域名称
     **/
    private String areaName;
    /**
     * 片区名称
     **/
    private String sheetName;

    private String cateName;
    /**
     * 0=租；1=售
     */
    private String sellRent;

    private Integer isShow;
    /**
     * 排序
     */
    private Integer orderBy;


    public String getSellRent() {
        return sellRent;
    }

    public void setSellRent(String sellRent) {
        this.sellRent = sellRent;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Popular{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (cateId != null) {
            sb.append(", \"cateId\":\"").append(cateId).append("\"");
        }
        if (menuId != null) {
            sb.append(", \"menuId\":\"").append(menuId).append("\"");
        }
        if (name != null) {
            sb.append(", \"name\":\"").append(name).append("\"");
        }
        if (source != null) {
            sb.append(", \"source\":\"").append(source).append("\"");
        }
        if (url != null) {
            sb.append(", \"url\":\"").append(url).append("\"");
        }
        if (provinceId != null) {
            sb.append(", \"provinceId\":\"").append(provinceId).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (areaId != null) {
            sb.append(", \"areaId\":\"").append(areaId).append("\"");
        }
        if (sheetId != null) {
            sb.append(", \"sheetId\":\"").append(sheetId).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (provinceName != null) {
            sb.append(", \"provinceName\":\"").append(provinceName).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (areaName != null) {
            sb.append(", \"areaName\":\"").append(areaName).append("\"");
        }
        if (sheetName != null) {
            sb.append(", \"sheetName\":\"").append(sheetName).append("\"");
        }
        return sb.toString();
    }
}