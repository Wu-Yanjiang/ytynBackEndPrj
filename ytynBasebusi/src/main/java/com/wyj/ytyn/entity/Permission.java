package com.wyj.ytyn.entity;

/**
 * 权限许可,及其能访问的资源
 */
public class Permission {

    private String name;        // 权限名

    private String resource;     //  资源

    private String description; // 描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
