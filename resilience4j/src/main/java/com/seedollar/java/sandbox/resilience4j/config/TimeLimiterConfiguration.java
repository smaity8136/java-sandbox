package com.seedollar.java.sandbox.resilience4j.config;

import com.codahale.metrics.MetricRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Configuration
public class TimeLimiterConfiguration {

    @Autowired
    private MetricRegistry metricRegistry;

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
