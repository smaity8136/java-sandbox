package com.seedollar.java.sandbox.resilience4j.config;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class TimeLimiterConfiguration {

    @Bean
    public TimeLimiterConfig customTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2)) // The timeout for thread execution
                .cancelRunningFuture(true) // Determine if cancel() is called on the target future.
                .build();
    }

    @Bean
    public TimeLimiter bookValueTimeLimiter() {
        return TimeLimiter.of(customTimeLimiterConfig());
    }
}
