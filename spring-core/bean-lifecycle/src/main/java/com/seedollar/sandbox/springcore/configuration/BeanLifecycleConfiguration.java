package com.seedollar.sandbox.springcore.configuration;

import com.seedollar.sandbox.springcore.pojo.SpringBeanPojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by seedollar on 8/16/17.
 */
@Configuration
public class BeanLifecycleConfiguration {

    @Bean
    public SpringBeanPojo springBeanPojo() {
        return new SpringBeanPojo();
    }
}
