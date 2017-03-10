package com.seedollar.spring.graphite.configuration;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by seedollar on 3/10/17.
 */
@Configuration
public class MeterMetricConfiguration {

    private MetricRegistry metricRegistry;

    public MeterMetricConfiguration(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Bean
    public Meter hitMeter() {
        return metricRegistry.meter("hitMeter");
    }
}
