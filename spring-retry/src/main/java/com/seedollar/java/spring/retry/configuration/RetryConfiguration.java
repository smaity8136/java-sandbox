package com.seedollar.java.spring.retry.configuration;

import com.seedollar.java.spring.retry.service.CalculationService;
import com.seedollar.java.spring.retry.service.impl.CalculationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Created by seedollar on 1/19/17.
 */
@Configuration
@EnableRetry
public class RetryConfiguration {

    @Bean
    public CalculationService calculationService() {
        return new CalculationServiceImpl();
    }

}
