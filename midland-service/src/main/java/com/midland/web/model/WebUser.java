package com.midland.web.model;

import java.io.Serializable;
import java.util.Date;

public class WebUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String userCnName;

    private String password;

    private Integer userType;

    private Integer state;

    private String email;

    private String phone;

    private String createBy;

    private Date createTime;

    private Date modifyTime;

    private Integer parentId;

    private String type;

    private Integer msgNum;

    private String custName;

    private String headImg;

    private String qqOpenId;

    private String wxOpenId;

    private String wbOpenId;

    private String blackRemark;
    /**
     * 身份证号码
     */
    private String identification;
    /**
     * 身份证照片url
     */
    private String idcartImg;
    /**
     * 身份证照片url
     */
    private String idcartImg1;

    private String actualName;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserCnName() {
        return userCnName;
    }

    public void setUserCnName(String userCnName) {
        this.userCnName = userCnName == null ? null : userCnName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(Integer msgNum) {
        this.msgNum = msgNum;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }


    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWbOpenId() {
        return wbOpenId;
    }

    public void setWbOpenId(String wbOpenId) {
        this.wbOpenId = wbOpenId;
    }

    public String getBlackRemark() {
        return blackRemark;
    }

    public void setBlackRemark(String blackRemark) {
        this.blackRemark = blackRemark;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getIdcartImg() {
        return idcartImg;
    }

    public void setIdcartImg(String idcartImg) {
        this.idcartImg = idcartImg;
    }

    public String getIdcartImg1() {
        return idcartImg1;
    }

    public void setIdcartImg1(String idcartImg1) {
        this.idcartImg1 = idcartImg1;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }
}