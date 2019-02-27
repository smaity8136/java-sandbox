package com.seedollar.java.sandbox.resilience4j.config;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BulkheadConfiguration {

    @Bean
    public BulkheadConfig customBulkHeadConfig() {
        return BulkheadConfig.custom()
                .maxConcurrentCalls(1)
                .maxWaitTime(1)
                .build();
    }

    @Bean
    public BulkheadRegistry bulkheadRegistry() {
        return BulkheadRegistry.of(customBulkHeadConfig());
    }

    @Bean
    public Bulkhead paymentServiceBulkhead() {
        return Bulkhead.of("paymentServiceBulkhead", this::customBulkHeadConfig);
    }
}
