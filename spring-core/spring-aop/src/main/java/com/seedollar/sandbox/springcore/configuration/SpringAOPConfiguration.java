package com.seedollar.sandbox.springcore.configuration;

import com.seedollar.sandbox.springcore.aspect.DrivingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SpringAOPConfiguration {

    @Bean
    public DrivingAspect drivingAspect() {
        return new DrivingAspect();
    }

}
