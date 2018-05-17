package com.midland.web.model.user;

import com.midland.web.model.role.Role;

import java.util.List;

/**
 * 用户模型
 *
 * @author
 * @since 2016年7月5日 下午12:07:20
 **/
public class User {
    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;
    /**
     * 用户名称
     */
    private String userCnName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类型：0=管理员；1=经纪人；2=前端用户
     */
    private Integer userType;
    /**
     * 状态（0失效，1生效，3删除）
     */
    private String state;

    private String createTime;

    private String createBy;

    /**
     * 头像图片
     */
    private String headImg;


    private String startTime;

    private String endTime;

    private String modifyTime;


    private Integer source;

    /**
     * 0未认证，1审核中，2审核通过，3拒绝
     */
    private Integer auditStatus;

    private String auditTime;
    /**
     * 0未加入黑名单
     * 1已加入黑名单
     */
    private String isBlack;

    /**
     * 加入黑名单原因
     */
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
    /**
     * 审核人
     */
    private String auditName;


    private String auditRemark;
    /**
     *
     */
    private String userRoles;

    private String phone;

    private String email;

    private List<Role> roles;

    private String flag;
    private String cityId;
    private String cityName;

    private String actualName;

    private String isSuper;
    /**
     * 备用
     */
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String flag) {
        this.username = username;
        this.password = password;
        this.flag = flag;
    }


    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getIdcartImg1() {
        return idcartImg1;
    }

    public void setIdcartImg1(String idcartImg1) {
        this.idcartImg1 = idcartImg1;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }


    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(String isBlack) {
        this.isBlack = isBlack;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserCnName() {
        return userCnName;
    }

    public void setUserCnName(String userCnName) {
        this.userCnName = userCnName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(String isSuper) {
        this.isSuper = isSuper;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", userCnName='").append(userCnName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userType=").append(userType);
        sb.append(", state='").append(state).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", modifyTime='").append(modifyTime).append('\'');
        sb.append(", source=").append(source);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", isBlack='").append(isBlack).append('\'');
        sb.append(", blackRemark='").append(blackRemark).append('\'');
        sb.append(", identification='").append(identification).append('\'');
        sb.append(", idcartImg='").append(idcartImg).append('\'');
        sb.append(", idcartImg1='").append(idcartImg1).append('\'');
        sb.append(", auditName='").append(auditName).append('\'');
        sb.append(", userRoles='").append(userRoles).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", roles=").append(roles);
        sb.append(", flag='").append(flag).append('\'');
        sb.append('}');
        return sb.toString();
    }
}