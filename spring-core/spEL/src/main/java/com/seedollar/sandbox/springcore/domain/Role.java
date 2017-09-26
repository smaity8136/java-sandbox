package com.seedollar.sandbox.springcore.domain;

public class Role {

    private String permission;

    private Integer level;


    public Role(String permission, Integer level) {
        this.permission = permission;
        this.level = level;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Role{" +
                "permission='" + permission + '\'' +
                ", level=" + level +
                '}';
    }
}
