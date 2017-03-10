package com.seedollar.spring.graphite.configuration;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.seedollar.spring.graphite.service.HitService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by seedollar on 3/10/17.
 */
@Configuration
public class CounterMetricConfiguration {

    private MetricRegistry metricRegistry;

    public CounterMetricConfiguration(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Bean
    public Counter hitCounter() {
        return this.metricRegistry.counter(MetricRegistry.name(HitService.class, "hit", "counter"));
    }
}
