package com.seedollar.java.sandbox.resilience4j.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreakerConfig customCircuitBreakerConfig() {
        // Define defaults for circuit breaker
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

        return CircuitBreakerConfig
                .custom()
                .failureRateThreshold(10.0f) // failure rate percentage, 10%
                // the number of calls that need to be evaluated to calculate the failure rate. For example, if the buffer is 100, then 100 calls need to be evaluated before
                // the failure rate can be calculated. So if 10 calls out of 100 calls in the buffer have failed, the circuitbreaker will transition to OPEN.
                .ringBufferSizeInClosedState(5)
                .waitDurationInOpenState(Duration.ofSeconds(20))
                .build();
    }

    @Bean
    public CircuitBreaker createAccountCircuitBreaker() {
        return CircuitBreaker.of("createAccountCircuitBreaker", customCircuitBreakerConfig());
    }
}
