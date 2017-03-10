package com.seedollar.spring.graphite.configuration;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.seedollar.spring.graphite.service.HitService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by seedollar on 3/10/17.
 */
@Configuration
public class HistogramMetricConfiguration {

    private MetricRegistry metricRegistry;

    public HistogramMetricConfiguration(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Bean
    public Histogram assassinationRateHistogram() {
        return metricRegistry.histogram(MetricRegistry.name(HitService.class, "hit", "rate"));
    }
}
