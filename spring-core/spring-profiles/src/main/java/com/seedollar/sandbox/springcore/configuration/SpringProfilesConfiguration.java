package com.seedollar.sandbox.springcore.configuration;

import com.seedollar.sandbox.springcore.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class SpringProfilesConfiguration {

    @Bean
    public Biller opsBiller() {
        return new OpsBiller("SCRIPTS");
    }

    @Bean
    public Container container() {
        return new Container("WEBSPHERE", 20);
    }

    @Bean
    @Profile("dev")
    @Primary
    public Biller devBiller() {
        return new DevBiller();
    }

    @Bean
    @Profile("perf")
    @Primary
    public Biller perfBiller() {
        return new PerfBiller();
    }

    @Bean
    @Profile("uat")
    @Primary
    public Biller uatBiller() {
        return new UatBiller();
    }

    @Bean
    @Profile("prod")
    @Primary
    public Biller prodBiller() {
        return new ProdBiller();
    }

}
