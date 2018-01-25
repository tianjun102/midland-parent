package com.midland.web.model;


public class Discount {
    /**
     * 折扣表
     **/
    private Integer id;
    /**
     * 选项名称
     **/
    private String name;
    /**
     * 折扣值
     **/
    private String discountVal;
    /**
     * 排序字段
     **/
    private Integer orderBy;
    /**
     * 0=正常；1删除
     **/
    private Integer isDelete;
    /**
     * 0=显示；1=隐藏
     **/
    private Integer isShow;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscountVal() {
        return discountVal;
    }

    public void setDiscountVal(String discountVal) {
        this.discountVal = discountVal;
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

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Discount{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (name != null) {
            sb.append(", \"name\":\"").append(name).append("\"");
        }
        if (discountVal != null) {
            sb.append(", \"discountVal\":\"").append(discountVal).append("\"");
        }
        if (orderBy != null) {
            sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
        }
        if (isDelete != null) {
            sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
        }
        if (isShow != null) {
            sb.append(", \"isShow\":\"").append(isShow).append("\"");
        }
        return sb.toString();
    }
}