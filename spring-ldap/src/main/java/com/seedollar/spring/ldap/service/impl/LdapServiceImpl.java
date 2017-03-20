package com.seedollar.spring.ldap.service.impl;

import com.seedollar.spring.ldap.domain.LdapUser;
import com.seedollar.spring.ldap.service.LdapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * Created by seedollar on 3/15/17.
 */
@Service
public class LdapServiceImpl implements LdapService {

    private LdapTemplate ldapTemplate;

    public LdapServiceImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return ldapTemplate.authenticate("", "cn="+ username, password);
    }

    @Override
    public void create(String username, String password) {
        Name dn = LdapNameBuilder
                .newInstance()
                .add("cn", username)
                .build();
        DirContextAdapter context = new DirContextAdapter(dn);

        context.setAttributeValues(
                "objectclass",
                new String[]
                        {"top",
                                "person",
                                "organizationalPerson",
                                "inetOrgPerson"});
        context.setAttributeValue("cn", username);
        context.setAttributeValue("sn", username);
        context.setAttributeValue
                ("userPassword", digestSHA(password));

        ldapTemplate.bind(context);
    }

    @Override
    public void modify(String username, String password) {

    }

    @Override
    public List<LdapUser> search(String username) {
        return ldapTemplate
                .search(
                        "",
                        "cn=" + username,
                        (AttributesMapper<LdapUser>) attrs -> {
                            LdapUser foundUser = new LdapUser((String) attrs.get("cn").get(), null);
                            return foundUser;
                        });
    }

    private String digestSHA(final String password) {
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(password.getBytes());
            base64 = Base64
                    .getEncoder()
                    .encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{SHA}" + base64;
    }
}
