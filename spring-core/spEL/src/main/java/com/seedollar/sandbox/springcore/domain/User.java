package com.seedollar.sandbox.springcore.domain;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class User {

    private String identifier;

    private String username;

    private boolean active;

    private String comment;

    private Double salary;

    private Integer[] tags;

    private List<Role> roles;

    public User(String identifier, String username, boolean active, String comment) {
        this.roles = Lists.newArrayList();
        this.tags = new Integer[10];
        this.identifier = identifier;
        this.username = username;
        this.active = active;
        this.comment = comment;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getSalary() {
        return salary;
    }

    public Integer[] getTags() {
        return tags;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier='" + identifier + '\'' +
                ", username='" + username + '\'' +
                ", active=" + active +
                ", comment='" + comment + '\'' +
                ", salary=" + salary +
                ", tags=" + Arrays.toString(tags) +
                ", roles=" + roles +
                '}';
    }
}
