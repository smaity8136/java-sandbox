package com.seedollar.sandbox.springcore.configuration;

import com.seedollar.sandbox.springcore.domain.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@Configuration
public class BeanScopingConfiguration {

    @Bean
    public President numberOne() {
        return new President(UUID.randomUUID().toString());
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MP createMP() {
        return new MP(UUID.randomUUID().toString());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Minister appointMinister() {
        return new Minister(UUID.randomUUID().toString());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
    public Judge appointJudge() {
        return new SupremeCourtJudge(UUID.randomUUID().toString());
    }
}
