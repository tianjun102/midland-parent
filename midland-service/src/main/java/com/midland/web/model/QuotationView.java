package com.midland.web.model;


public class QuotationView {
    /**
     *
     **/
    private Integer id;
    /**
     *
     **/
    private String cityId;
    /**
     *
     **/
    private String areaId;
    /**
     *
     **/
    private Integer type;
    /**
     *
     **/
    private String sliceId;
    /**
     *
     **/
    private String updateTime;
    /**
     *
     **/
    private Integer isDelete;
    /**
     *
     **/
    private String cityName;
    /**
     *
     **/
    private String areaName;
    /**
     *
     **/
    private String sliceName;
    /**
     *
     **/
    private String houseAcreage;
    /**
     *
     **/
    private String dataTime;
    /**
     *
     **/
    private Integer dealNum;
    /**
     *
     **/
    private Double preDealNum;
    /**
     *
     **/
    private String dealAcreage;
    /**
     *
     **/
    private Double preDealAcreage;
    /**
     *
     **/
    private String price;
    /**
     *
     **/
    private Double prePrice;
    /**
     *
     **/
    private Integer soldNum;
    /**
     *
     **/
    private Double preSoldNum;
    /**
     *
     **/
    private String soldArea;
    /**
     *
     **/
    private Double preSoldArea;
    /**
     *
     **/
    private String dealPrice;
    /**
     *
     **/
    private Double preDealPrice;
    private String startTime;
    private String endTime;

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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSliceId() {
        return sliceId;
    }

    public void setSliceId(String sliceId) {
        this.sliceId = sliceId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSliceName() {
        return sliceName;
    }

    public void setSliceName(String sliceName) {
        this.sliceName = sliceName;
    }

    public String getHouseAcreage() {
        return houseAcreage;
    }

    public void setHouseAcreage(String houseAcreage) {
        this.houseAcreage = houseAcreage;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }

    public Double getPreDealNum() {
        return preDealNum;
    }

    public void setPreDealNum(Double preDealNum) {
        this.preDealNum = preDealNum;
    }

    public String getDealAcreage() {
        return dealAcreage;
    }

    public void setDealAcreage(String dealAcreage) {
        this.dealAcreage = dealAcreage;
    }

    public Double getPreDealAcreage() {
        return preDealAcreage;
    }

    public void setPreDealAcreage(Double preDealAcreage) {
        this.preDealAcreage = preDealAcreage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(Double prePrice) {
        this.prePrice = prePrice;
    }

    public Integer getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(Integer soldNum) {
        this.soldNum = soldNum;
    }

    public Double getPreSoldNum() {
        return preSoldNum;
    }

    public void setPreSoldNum(Double preSoldNum) {
        this.preSoldNum = preSoldNum;
    }

    public String getSoldArea() {
        return soldArea;
    }

    public void setSoldArea(String soldArea) {
        this.soldArea = soldArea;
    }

    public Double getPreSoldArea() {
        return preSoldArea;
    }

    public void setPreSoldArea(Double preSoldArea) {
        this.preSoldArea = preSoldArea;
    }

    public String getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(String dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Double getPreDealPrice() {
        return preDealPrice;
    }

    public void setPreDealPrice(Double preDealPrice) {
        this.preDealPrice = preDealPrice;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QuotationView{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (areaId != null) {
            sb.append(", \"areaId\":\"").append(areaId).append("\"");
        }
        if (type != null) {
            sb.append(", \"type\":\"").append(type).append("\"");
        }
        if (sliceId != null) {
            sb.append(", \"sliceId\":\"").append(sliceId).append("\"");
        }
        if (updateTime != null) {
            sb.append(", \"updateTime\":\"").append(updateTime).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (areaName != null) {
            sb.append(", \"areaName\":\"").append(areaName).append("\"");
        }
        if (sliceName != null) {
            sb.append(", \"sliceName\":\"").append(sliceName).append("\"");
        }
        if (houseAcreage != null) {
            sb.append(", \"houseAcreage\":\"").append(houseAcreage).append("\"");
        }
        if (dataTime != null) {
            sb.append(", \"dataTime\":\"").append(dataTime).append("\"");
        }
        if (dealNum != null) {
            sb.append(", \"dealNum\":\"").append(dealNum).append("\"");
        }
        if (preDealNum != null) {
            sb.append(", \"preDealNum\":\"").append(preDealNum).append("\"");
        }
        if (dealAcreage != null) {
            sb.append(", \"dealAcreage\":\"").append(dealAcreage).append("\"");
        }
        if (preDealAcreage != null) {
            sb.append(", \"preDealAcreage\":\"").append(preDealAcreage).append("\"");
        }
        if (price != null) {
            sb.append(", \"price\":\"").append(price).append("\"");
        }
        if (prePrice != null) {
            sb.append(", \"prePrice\":\"").append(prePrice).append("\"");
        }
        if (soldNum != null) {
            sb.append(", \"soldNum\":\"").append(soldNum).append("\"");
        }
        if (preSoldNum != null) {
            sb.append(", \"preSoldNum\":\"").append(preSoldNum).append("\"");
        }
        if (soldArea != null) {
            sb.append(", \"soldArea\":\"").append(soldArea).append("\"");
        }
        if (preSoldArea != null) {
            sb.append(", \"preSoldArea\":\"").append(preSoldArea).append("\"");
        }
        if (dealPrice != null) {
            sb.append(", \"dealPrice\":\"").append(dealPrice).append("\"");
        }
        if (preDealPrice != null) {
            sb.append(", \"preDealPrice\":\"").append(preDealPrice).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }
}