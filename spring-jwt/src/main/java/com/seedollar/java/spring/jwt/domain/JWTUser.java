package com.seedollar.java.spring.jwt.domain;

/**
 * Created by seedollar on 2/21/17.
 */
public class JWTUser {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
