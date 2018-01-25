package com.midland.web.model;


public class PageConf {
    /**
     * 页面配置表id  CNZZ配置
     **/
    private Integer id;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 模块
     **/
    private Integer model;
    /**
     * meta标签
     **/
    private String metaLable;
    /**
     * 是否设置meta标签；0=设置；1=未设置
     **/
    private Integer metaShow;
    /**
     * 百度计量代码
     **/
    private String baiduCode;
    /**
     * 是否设置百度计量
     **/
    private Integer baiduShow;
    /**
     * cnzz流量统计代码
     **/
    private String cnzzCode;
    /**
     * cnzz微站代码
     **/
    private String cnzzCodeWechat;
    /**
     * cnzz 微站代码
     **/
    private String baiduCodeWechat;
    /**
     * meta描述
     **/
    private String metaDesc;
    /**
     * 标题
     **/
    private String title;
    /**
     * 是否删除；0=正常；1=删除
     **/
    private Integer isDelete;
    /**
     * 是否删除；0=网站；1=微站
     **/
    private Integer source;


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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getMetaLable() {
        return metaLable;
    }

    public void setMetaLable(String metaLable) {
        this.metaLable = metaLable;
    }

    public Integer getMetaShow() {
        return metaShow;
    }

    public void setMetaShow(Integer metaShow) {
        this.metaShow = metaShow;
    }

    public String getBaiduCode() {
        return baiduCode;
    }

    public void setBaiduCode(String baiduCode) {
        this.baiduCode = baiduCode;
    }

    public Integer getBaiduShow() {
        return baiduShow;
    }

    public void setBaiduShow(Integer baiduShow) {
        this.baiduShow = baiduShow;
    }

    public String getCnzzCode() {
        return cnzzCode;
    }

    public void setCnzzCode(String cnzzCode) {
        this.cnzzCode = cnzzCode;
    }

    public String getCnzzCodeWechat() {
        return cnzzCodeWechat;
    }

    public void setCnzzCodeWechat(String cnzzCodeWechat) {
        this.cnzzCodeWechat = cnzzCodeWechat;
    }

    public String getBaiduCodeWechat() {
        return baiduCodeWechat;
    }

    public void setBaiduCodeWechat(String baiduCodeWechat) {
        this.baiduCodeWechat = baiduCodeWechat;
    }

    public String getMetaDesc() {
        return metaDesc;
    }

    public void setMetaDesc(String metaDesc) {
        this.metaDesc = metaDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageConf{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (model != null) {
            sb.append(", \"model\":\"").append(model).append("\"");
        }
        if (metaLable != null) {
            sb.append(", \"metaLable\":\"").append(metaLable).append("\"");
        }
        if (metaShow != null) {
            sb.append(", \"metaShow\":\"").append(metaShow).append("\"");
        }
        if (baiduCode != null) {
            sb.append(", \"baiduCode\":\"").append(baiduCode).append("\"");
        }
        if (baiduShow != null) {
            sb.append(", \"baiduShow\":\"").append(baiduShow).append("\"");
        }
        if (cnzzCode != null) {
            sb.append(", \"cnzzCode\":\"").append(cnzzCode).append("\"");
        }
        if (cnzzCodeWechat != null) {
            sb.append(", \"cnzzCodeWechat\":\"").append(cnzzCodeWechat).append("\"");
        }
        if (baiduCodeWechat != null) {
            sb.append(", \"baiduCodeWechat\":\"").append(baiduCodeWechat).append("\"");
        }
        if (metaDesc != null) {
            sb.append(", \"metaDesc\":\"").append(metaDesc).append("\"");
        }
        if (title != null) {
            sb.append(", \"title\":\"").append(title).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (source != null) {
            sb.append(", \"source\":\"").append(source).append("\"");
        }
        return sb.toString();
    }
}