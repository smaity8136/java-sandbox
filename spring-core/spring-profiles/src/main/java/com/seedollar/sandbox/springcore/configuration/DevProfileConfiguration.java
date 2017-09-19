package com.seedollar.sandbox.springcore.configuration;

import com.seedollar.sandbox.springcore.domain.Container;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevProfileConfiguration {

    @Bean
    public Container container() {
        return new Container("JETTY", 5);
    }
}
