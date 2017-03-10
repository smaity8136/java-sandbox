package com.seedollar.spring.graphite.configuration;

import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by seedollar on 3/10/17.
 */
@Configuration
public class MetricRegistryConfiguration {

    @Bean
    public MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }
}
