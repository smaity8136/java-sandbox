package com.seedollar.java.sandbox.resilience4j.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimiterConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterConfiguration.class);

    @Bean
    public RateLimiterConfig customRateLimitConfig() {
        return RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1)) // The period which defines the rate of requests to be considered. i.e. per second
                .limitForPeriod(5) // The number of requests per the limit refresh period above i.e. 5 requests per second.
                .build();
    }

    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        return RateLimiterRegistry.of(customRateLimitConfig());
    }

    @Bean
    public RateLimiter getAllAccountsRateLimiter() {
        RateLimiter getAllAccountsRateLimiter = RateLimiter.of("getAllAccountsRateLimiter", this::customRateLimitConfig);
        getAllAccountsRateLimiter.getEventPublisher()
                .onFailure(evt -> logger.info("RateLimiter Failed - {} - {}", evt.getEventType().name(), evt.getRateLimiterName()))
                .onSuccess(evt -> logger.info("RateLimiter Succeeded - {} - {}", evt.getEventType().name(), evt.getRateLimiterName()));
        return getAllAccountsRateLimiter;
    }
}
