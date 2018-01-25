package com.midland.web.model;

import java.io.Serializable;

public class Region implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1273036970504998312L;

    private String regionId;

    private String parentId;

    private String regionName;

    private String regionType;

    private String agencyId;

    private String regionSn;

    private String buildin;

    private String lastchanged;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getRegionSn() {
        return regionSn;
    }

    public void setRegionSn(String regionSn) {
        this.regionSn = regionSn;
    }

    public String getBuildin() {
        return buildin;
    }

    public void setBuildin(String buildin) {
        this.buildin = buildin;
    }

    public String getLastchanged() {
        return lastchanged;
    }

    public void setLastchanged(String lastchanged) {
        this.lastchanged = lastchanged;
    }


}