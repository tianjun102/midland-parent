package com.midland.web.model;


import java.util.List;

public class HotHand {
    /**
     * 热卖一手主键id
     **/
    private Integer id;
    /**
     * 均价
     **/
    private String price;
    /**
     * 入伙日期
     **/
    private String intoTime;
    /**
     * 管理费
     **/
    private String managerCosts;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 单位总数
     **/
    private String unitTotal;
    /**
     * 占地面积
     **/
    private String landArea;
    /**
     * 车位总数
     **/
    private String parkingNum;
    /**
     * 建筑类型
     **/
    private String buildingType;
    /**
     * 物业地址
     **/
    private String propertyAddress;
    /**
     * 发展商
     **/
    private String developer;
    /**
     * 装修0豪装，1精装，2普装，3毛坯
     **/
    private Integer decoration;
    /**
     * 物业管理
     **/
    private String propertyManagement;
    /**
     * 房源描述
     **/
    private String propertyDesc;
    /**
     * 物业优点
     **/
    private String propertyAdvantages;
    /**
     * 地理位置
     **/
    private String position;
    /**
     * 配套信息
     **/
    private String supporting;
    /**
     * 房源名
     **/
    private String houseName;
    /**
     * 图片url
     **/
    private String imgUrl;
    /**
     * 经纪人名
     **/
    private String agentName;
    /**
     * 经纪人id
     **/
    private String agentId;
    /**
     * 创建时间
     **/
    private String createTime;
    /**
     * 是否删除：0未删除，1已删除
     **/
    private Integer isDelete;
    /**
     * 排序字段
     **/
    private Integer orderBy;
    /**
     * 是否展示：0展示，1隐藏
     **/
    private Integer isShow;

    private List<String> imgUrlList;
    private String startTime;
    private String endTime;
    /**
     * 特色
     */
    private String feature;
    /**
     * 主推户型
     */
    private String recommend;

    private Integer sort;
    /**
     * 建筑面积
     */
    private Integer buildingArea;


    public Integer getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Integer buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(String intoTime) {
        this.intoTime = intoTime;
    }

    public String getManagerCosts() {
        return managerCosts;
    }

    public void setManagerCosts(String managerCosts) {
        this.managerCosts = managerCosts;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUnitTotal() {
        return unitTotal;
    }

    public void setUnitTotal(String unitTotal) {
        this.unitTotal = unitTotal;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(String parkingNum) {
        this.parkingNum = parkingNum;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Integer getDecoration() {
        return decoration;
    }

    public void setDecoration(Integer decoration) {
        this.decoration = decoration;
    }

    public String getPropertyManagement() {
        return propertyManagement;
    }

    public void setPropertyManagement(String propertyManagement) {
        this.propertyManagement = propertyManagement;
    }

    public String getPropertyDesc() {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public String getPropertyAdvantages() {
        return propertyAdvantages;
    }

    public void setPropertyAdvantages(String propertyAdvantages) {
        this.propertyAdvantages = propertyAdvantages;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSupporting() {
        return supporting;
    }

    public void setSupporting(String supporting) {
        this.supporting = supporting;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HotHand{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (price != null) {
            sb.append(", \"price\":\"").append(price).append("\"");
        }
        if (intoTime != null) {
            sb.append(", \"intoTime\":\"").append(intoTime).append("\"");
        }
        if (managerCosts != null) {
            sb.append(", \"managerCosts\":\"").append(managerCosts).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (unitTotal != null) {
            sb.append(", \"unitTotal\":\"").append(unitTotal).append("\"");
        }
        if (landArea != null) {
            sb.append(", \"landArea\":\"").append(landArea).append("\"");
        }
        if (parkingNum != null) {
            sb.append(", \"parkingNum\":\"").append(parkingNum).append("\"");
        }
        if (buildingType != null) {
            sb.append(", \"buildingType\":\"").append(buildingType).append("\"");
        }
        if (propertyAddress != null) {
            sb.append(", \"propertyAddress\":\"").append(propertyAddress).append("\"");
        }
        if (developer != null) {
            sb.append(", \"developer\":\"").append(developer).append("\"");
        }
        if (decoration != null) {
            sb.append(", \"decoration\":\"").append(decoration).append("\"");
        }
        if (propertyManagement != null) {
            sb.append(", \"propertyManagement\":\"").append(propertyManagement).append("\"");
        }
        if (propertyDesc != null) {
            sb.append(", \"propertyDesc\":\"").append(propertyDesc).append("\"");
        }
        if (propertyAdvantages != null) {
            sb.append(", \"propertyAdvantages\":\"").append(propertyAdvantages).append("\"");
        }
        if (position != null) {
            sb.append(", \"position\":\"").append(position).append("\"");
        }
        if (supporting != null) {
            sb.append(", \"supporting\":\"").append(supporting).append("\"");
        }
        if (houseName != null) {
            sb.append(", \"houseName\":\"").append(houseName).append("\"");
        }
        if (imgUrl != null) {
            sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
        }
        if (agentName != null) {
            sb.append(", \"agentName\":\"").append(agentName).append("\"");
        }
        if (agentId != null) {
            sb.append(", \"agentId\":\"").append(agentId).append("\"");
        }
        if (createTime != null) {
            sb.append(", \"createTime\":\"").append(createTime).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (orderBy != null) {
            sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
        }
        if (isShow != null) {
            sb.append(", \"isShow\":\"").append(isShow).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }
}