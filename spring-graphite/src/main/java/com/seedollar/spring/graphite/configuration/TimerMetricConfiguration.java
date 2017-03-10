package com.seedollar.spring.graphite.configuration;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.seedollar.spring.graphite.service.HitService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by seedollar on 3/10/17.
 */
@Configuration
public class TimerMetricConfiguration {

    private MetricRegistry metricRegistry;

    public TimerMetricConfiguration(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Bean
    public Timer hitEvenIntervalTimer() {
       return metricRegistry.timer(MetricRegistry.name(HitService.class, "hit", "even", "interval"));
    }
}
