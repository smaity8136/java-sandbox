package com.seedollar.spring.graphite.configuration;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * Created by seedollar on 3/10/17.
 */
@Configuration
public class GaugeMetricConfiguration {

    private MetricRegistry metricRegistry;

    public GaugeMetricConfiguration(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;

        this.metricRegistry.register("hitGauge", (Gauge<Integer>) () -> new Random().nextInt());
    }


}
