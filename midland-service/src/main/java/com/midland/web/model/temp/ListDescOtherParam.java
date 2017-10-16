package com.midland.web.model.temp;

public class ListDescOtherParam {
    /**
     *  城市id
     */
    private String cityId;
    /**
     * 类型
     */
    private String type;
    /**
     * 平台，0网站，1微站
     */
    private Integer source;


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
