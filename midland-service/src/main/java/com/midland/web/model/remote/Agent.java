package com.midland.web.model.remote;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/9/18.
 */
public class Agent {
    /**
     * 手机
     */
    private String phone;
    /**
     * 职位
     */
    private String post;
    /**
     * 在线沟通
     */
    private String IM_ID;
    /**
     * 服务类型
     */
    private String serviceType;
    /**
     * 服务区域
     */
    private String serviceAreaName;
    /**
     * 微信名片
     */
    private String url;
    /**
     * 经纪人工号
     */
    private String jobNum;
    /**
     * 熟悉小区
     */
    private String knowGardenName;
    /**
     * id
     */
    private String id;
    /**
     * 二手在租套数
     */
    private String rentCount;
    /**
     * 二手在售套数
     */
    private String saleCount;
    /**
     * 门店
     */
    private String storeName;
    /**
     * 名称
     */
    private String name;
    /**
     * 店铺地址
     */
    private String photoUrl;

    /**
     * 领导手机号码
     */
    private String leaderPhone;
    /**
     * 领导名称
     */
    private String leaderName;
    /**
     * 领导邮箱
     */
    private String leaderEmail;


    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

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

    public String getIM_ID() {
        return IM_ID;
    }

    public void setIM_ID(String IM_ID) {
        this.IM_ID = IM_ID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceAreaName() {
        return serviceAreaName;
    }

    public void setServiceAreaName(String serviceAreaName) {
        this.serviceAreaName = serviceAreaName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getKnowGardenName() {
        return knowGardenName;
    }

    public void setKnowGardenName(String knowGardenName) {
        this.knowGardenName = knowGardenName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRentCount() {
        return rentCount;
    }

    public void setRentCount(String rentCount) {
        this.rentCount = rentCount;
    }

    public String getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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


    public Map agentToMap() {
        Map map = new HashMap();
        if (phone != null) {
            map.put("phone", phone);
        }
        if (post != null) {
            map.put("post", post);
        }
        if (IM_ID != null) {
            map.put("IM_ID", IM_ID);
        }
        if (serviceType != null) {
            map.put("serviceType", serviceType);
        }
        if (serviceAreaName != null) {
            map.put("serviceAreaName", serviceAreaName);
        }
        if (url != null) {
            map.put("url", url);
        }
        if (jobNum != null) {
            map.put("jobNum", jobNum);
        }
        if (knowGardenName != null) {
            map.put("knowGardenName", knowGardenName);
        }
        if (id != null) {
            map.put("id", id);
        }
        if (rentCount != null) {
            map.put("rentCount", rentCount);
        }
        if (saleCount != null) {
            map.put("saleCount", saleCount);
        }
        if (name != null) {
            map.put("name", name);
        }

        if (photoUrl != null) {
            map.put("photoUrl", photoUrl);
        }
        if (storeName != null) {
            map.put("storeName", storeName);
        }

        return map;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Agent{");
        sb.append("phone='").append(phone).append('\'');
        sb.append(", post='").append(post).append('\'');
        sb.append(", IM_ID='").append(IM_ID).append('\'');
        sb.append(", serviceType='").append(serviceType).append('\'');
        sb.append(", serviceAreaName='").append(serviceAreaName).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", jobNum='").append(jobNum).append('\'');
        sb.append(", knowGardenName='").append(knowGardenName).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", rentCount='").append(rentCount).append('\'');
        sb.append(", saleCount='").append(saleCount).append('\'');
        sb.append(", storeName='").append(storeName).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", photoUrl='").append(photoUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
