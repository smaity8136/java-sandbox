package com.seedollar.java.sandbox.resilience4j.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    private ConfigurableBeanFactory beanFactory;

    private static final String DELETE_ACCOUNT_CIRCUIT_BREAKER_NAME = "deleteAccount";

    @Bean
    public CircuitBreakerConfig customCircuitBreakerConfig() {
        // Define defaults for circuit breaker
//        CircuitBreakerRegistry customCircuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

        return CircuitBreakerConfig
                .custom()
                .failureRateThreshold(50.0f) // failure rate percentage, 50%
                // the number of calls that need to be evaluated to calculate the failure rate. For example, if the buffer is 10, then 10 calls need to be evaluated before
                // the failure rate can be calculated. So if 1 call out of 10 calls in the buffer have failed, the circuitbreaker will transition to OPEN.
                .ringBufferSizeInClosedState(10)
                // the number of calls that need to be evaluated to calculate the failure rate when the circuit breaker is in HALF-OPEN state. Meaning that 8 calls need to be
                // recorded and the failure rate not at 50% or above before the circuitbreaker transitions from HALF-OPEN to CLOSED.
                .ringBufferSizeInHalfOpenState(8)
                .waitDurationInOpenState(Duration.ofSeconds(9)) // Defines how long the circuit breaker will remain OPEN before transitioning to HALF-OPEN state.
                .build();
    }

    @Bean
    public CircuitBreakerRegistry customCircuitBreakerRegistry() {
        return CircuitBreakerRegistry.of(customCircuitBreakerConfig());
    }

    @Bean
    public CircuitBreaker deleteAccountCircuitBreaker() {
        return circuitBreakerRegistry.circuitBreaker("deleteAccount", customCircuitBreakerConfig());
    }
}
