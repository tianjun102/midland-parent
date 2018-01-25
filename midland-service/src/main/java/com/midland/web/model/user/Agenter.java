package com.midland.web.model.user;

/**
 * 用户模型
 *
 * @author
 * @since 2016年7月5日 下午12:07:20
 **/
public class Agenter {
    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    private String photoUrl;

    private String phone;

    private String post;

    private String storeName;

    private Integer saleCount;

    private Integer rentCount;

    private String serviceAreaName;

    private String knowGardenName;

    private String IM_ID;

    private String url;

    private String leaderPhone;

    private String leaderEmail;

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    public String getLeaderEmail() {
        return leaderEmail;
    }

    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getRentCount() {
        return rentCount;
    }

    public void setRentCount(Integer rentCount) {
        this.rentCount = rentCount;
    }

    public String getServiceAreaName() {
        return serviceAreaName;
    }

    public void setServiceAreaName(String serviceAreaName) {
        this.serviceAreaName = serviceAreaName;
    }

    public String getKnowGardenName() {
        return knowGardenName;
    }

    public void setKnowGardenName(String knowGardenName) {
        this.knowGardenName = knowGardenName;
    }

    public String getIM_ID() {
        return IM_ID;
    }

    public void setIM_ID(String IM_ID) {
        this.IM_ID = IM_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}