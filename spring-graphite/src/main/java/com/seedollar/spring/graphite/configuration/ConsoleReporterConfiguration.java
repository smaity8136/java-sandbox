package com.seedollar.spring.graphite.configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by seedollar on 3/10/17.
 */
@Configuration
public class ConsoleReporterConfiguration {

    private MetricRegistry metricRegistry;

    public ConsoleReporterConfiguration(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Bean
    public ConsoleReporter consoleReporter() {
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        consoleReporter.start(1, TimeUnit.SECONDS);
        return consoleReporter;
    }

    @Bean
    public Meter consoleMeter() {
        return metricRegistry.meter("consoleHit");
    }
}
