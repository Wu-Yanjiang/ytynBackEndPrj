package com.wyj.ytyn.entity;

import java.util.List;

/**
 * 角色类
 */
public class Role {

    private String roleName;    // 角色名

    private String description; // 描述

    private List<Permission> permissions;   // 一个角色多个权限

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
