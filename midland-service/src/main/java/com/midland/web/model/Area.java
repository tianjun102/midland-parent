package com.midland.web.model;


/**
 * @author tianj
 * 区域实体类
 */
public class Area implements java.io.Serializable{

    private static final long serialVersionUID = 8456182291437212997L;

    private String id ;

    private String parentId;

    private String name;

    private String parentName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
