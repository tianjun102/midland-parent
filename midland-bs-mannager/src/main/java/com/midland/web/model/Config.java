package com.midland.web.model;

public class Config {
    private Integer id;

    private String supporting;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupporting() {
        return supporting;
    }

    public void setSupporting(String supporting) {
        this.supporting = supporting == null ? null : supporting.trim();
    }
}