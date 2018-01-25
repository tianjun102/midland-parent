package com.midland.web.model;


import java.util.List;

/**
 * 权限关系临时对象
 *
 * @author Administrator
 */
public class AuthRelation {

    private String tagName;

    private String tagLevel;

    private String url;

    private String fatherCode;

    private String code;

    private Long authId;

    private List<AuthRelation> authList;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagLevel() {
        return tagLevel;
    }

    public void setTagLevel(String tagLevel) {
        this.tagLevel = tagLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFatherCode() {
        return fatherCode;
    }

    public void setFatherCode(String fatherCode) {
        this.fatherCode = fatherCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public List<AuthRelation> getAuthList() {
        return authList;
    }

    public void setAuthList(List<AuthRelation> authList) {
        this.authList = authList;
    }

}
