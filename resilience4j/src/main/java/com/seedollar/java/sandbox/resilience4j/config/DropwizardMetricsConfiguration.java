package com.seedollar.java.sandbox.resilience4j.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Configuration
public class DropwizardMetricsConfiguration {

    @Bean
    public MetricRegistry metricRegistry() {
        MetricRegistry metricRegistry = new MetricRegistry();

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        // Startup JMX Reporter
        JmxReporter jmsReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmsReporter.start();

        return metricRegistry;
    }

    @Bean
    public HealthCheckRegistry healthCheckRegistry() {
        return new HealthCheckRegistry();
    }

    @PostConstruct
    public void registerCustomHealthCheckMetrics() {
        healthCheckRegistry().register("mainframeHealthCheck", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                if (ThreadLocalRandom.current().nextBoolean()) {
                    return Result.healthy();
                }
                return Result.unhealthy("Mainframe is not up!");
            }
        });
    }
}
