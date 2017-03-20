package com.seedollar.spring.ldap.service;

import com.seedollar.spring.ldap.domain.LdapUser;

import java.util.List;

/**
 * Created by seedollar on 3/14/17.
 */
public interface LdapService {

    boolean authenticate(String username, String password);

    void create(String username, String password);

    void modify(String username, String password);

    List<LdapUser> search(String username);
}
