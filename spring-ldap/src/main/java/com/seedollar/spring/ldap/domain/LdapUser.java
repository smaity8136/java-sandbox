package com.seedollar.spring.ldap.domain;

/**
 * Created by seedollar on 3/14/17.
 */
public class LdapUser {

    private String username;
    private String password;

    public LdapUser() {
    }

    public LdapUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
