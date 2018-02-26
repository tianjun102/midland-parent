package com.midland.web.model;

/**
 * 招聘管理
 */
public class RecruitManager {
    /**
     * 招聘管理表id
     **/
    private Integer id;
    /**
     * 招聘类型；1=校招；2=社招
     **/
    private Integer type;
    /**
     * 岗位
     **/
    private String post;
    /**
     * 招聘人数
     **/
    private Integer recruitersNum;
    /**
     * 学历要求；0=不限；1=高中；2=大专；3=本科；4=硕士及硕士以上
     **/
    private Integer education;
    /**
     * 工作年限； 0=不限；1=应届；2=1-3年；3=3-5年；4=5-10年；10年以上
     **/
    private Integer workLift;
    /**
     * 开始时间
     **/
    private String startTime;
    /**
     * 结束时间
     **/
    private String endTime;
    /**
     * 0=已发布；1=未发布
     **/
    private Integer releaseStatus;
    /**
     * 岗位描述
     **/
    private String postDesc;
    /**
     * 任职要求
     **/
    private String jobClaim;
    /**
     * 城市id
     **/
    private String cityId;
    /**
     * 城市名称
     **/
    private String cityName;
    /**
     * 发布时间
     **/
    private String releaseTime;
    /**
     * 接收邮箱
     **/
    private String email;

    private int isDelete;

    private String onlineTime;
    /**
     * 职位类别,0开发类,1设计类,2销售类,3运维类,4产品类,5金融类,6职能类
     */
    private String category;
    /**
     * 工作分组,0前线,1后勤
     */
    private String group;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getRecruitersNum() {
        return recruitersNum;
    }

    public void setRecruitersNum(Integer recruitersNum) {
        this.recruitersNum = recruitersNum;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getWorkLift() {
        return workLift;
    }

    public void setWorkLift(Integer workLift) {
        this.workLift = workLift;
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

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getJobClaim() {
        return jobClaim;
    }

    public void setJobClaim(String jobClaim) {
        this.jobClaim = jobClaim;
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

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RecruitManager{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (type != null) {
            sb.append(", \"type\":\"").append(type).append("\"");
        }
        if (post != null) {
            sb.append(", \"post\":\"").append(post).append("\"");
        }
        if (recruitersNum != null) {
            sb.append(", \"recruitersNum\":\"").append(recruitersNum).append("\"");
        }
        if (education != null) {
            sb.append(", \"education\":\"").append(education).append("\"");
        }
        if (workLift != null) {
            sb.append(", \"workLift\":\"").append(workLift).append("\"");
        }
        if (startTime != null) {
            sb.append(", \"startTime\":\"").append(startTime).append("\"");
        }
        if (endTime != null) {
            sb.append(", \"endTime\":\"").append(endTime).append("\"");
        }
        if (releaseStatus != null) {
            sb.append(", \"releaseStatus\":\"").append(releaseStatus).append("\"");
        }
        if (postDesc != null) {
            sb.append(", \"postDesc\":\"").append(postDesc).append("\"");
        }
        if (jobClaim != null) {
            sb.append(", \"jobClaim\":\"").append(jobClaim).append("\"");
        }
        if (cityId != null) {
            sb.append(", \"cityId\":\"").append(cityId).append("\"");
        }
        if (cityName != null) {
            sb.append(", \"cityName\":\"").append(cityName).append("\"");
        }
        if (releaseTime != null) {
            sb.append(", \"releaseTime\":\"").append(releaseTime).append("\"");
        }
        if (email != null) {
            sb.append(", \"email\":\"").append(email).append("\"");
        }
        return sb.toString();
    }
}