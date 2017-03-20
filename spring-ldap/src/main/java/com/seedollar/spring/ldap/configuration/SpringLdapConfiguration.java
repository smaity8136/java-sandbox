package com.seedollar.spring.ldap.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Created by seedollar on 3/14/17.
 */
@Configuration
public class SpringLdapConfiguration {

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.partitionSuffix}")
    private String partitionSuffix;

    @Value("${ldap.principal}")
    private String ldapPrincipal;

    @Value("${ldap.password}")
    private String ldapPassword;

    @Bean
    public ContextSource ldapContextSource() {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl(ldapUrl);
        ldapContextSource.setBase(partitionSuffix);
        ldapContextSource.setUserDn(ldapPrincipal);
        ldapContextSource.setPassword(ldapPassword);
        return ldapContextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(ldapContextSource());
    }
}
