package com.midland.web.model;


public class IntoMidland {
    /**
     * 招聘管理表主键id
     **/
    private Integer id;
    /**
     * 公司介绍
     **/
    private String companyProfile;
    /**
     * 公司历程
     **/
    private String companyProcess;
    /**
     * 公司荣誉
     **/
    private String companyHonor;
    /**
     * 公司培训
     **/
    private String companyTraining;
    /**
     * 公司文化
     **/
    private String companyCulture;
    /**
     * 晋升福利
     **/
    private String promotionBenefits;
    /**
     * 联系我们
     **/
    private String contactUs;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getCompanyProcess() {
        return companyProcess;
    }

    public void setCompanyProcess(String companyProcess) {
        this.companyProcess = companyProcess;
    }

    public String getCompanyHonor() {
        return companyHonor;
    }

    public void setCompanyHonor(String companyHonor) {
        this.companyHonor = companyHonor;
    }

    public String getCompanyTraining() {
        return companyTraining;
    }

    public void setCompanyTraining(String companyTraining) {
        this.companyTraining = companyTraining;
    }

    public String getCompanyCulture() {
        return companyCulture;
    }

    public void setCompanyCulture(String companyCulture) {
        this.companyCulture = companyCulture;
    }

    public String getPromotionBenefits() {
        return promotionBenefits;
    }

    public void setPromotionBenefits(String promotionBenefits) {
        this.promotionBenefits = promotionBenefits;
    }

    public String getContactUs() {
        return contactUs;
    }

    public void setContactUs(String contactUs) {
        this.contactUs = contactUs;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IntoMidland{");
        if (id != null) {
            sb.append(", \"id\":\"").append(id).append("\"");
        }
        if (companyProfile != null) {
            sb.append(", \"companyProfile\":\"").append(companyProfile).append("\"");
        }
        if (companyProcess != null) {
            sb.append(", \"companyProcess\":\"").append(companyProcess).append("\"");
        }
        if (companyHonor != null) {
            sb.append(", \"companyHonor\":\"").append(companyHonor).append("\"");
        }
        if (companyTraining != null) {
            sb.append(", \"companyTraining\":\"").append(companyTraining).append("\"");
        }
        if (companyCulture != null) {
            sb.append(", \"companyCulture\":\"").append(companyCulture).append("\"");
        }
        if (promotionBenefits != null) {
            sb.append(", \"promotionBenefits\":\"").append(promotionBenefits).append("\"");
        }
        if (contactUs != null) {
            sb.append(", \"contactUs\":\"").append(contactUs).append("\"");
        }
        return sb.toString();
    }
}