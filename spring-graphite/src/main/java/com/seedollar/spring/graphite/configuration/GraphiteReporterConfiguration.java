package com.seedollar.spring.graphite.configuration;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by seedollar on 3/10/17.
 */
@Configuration
public class GraphiteReporterConfiguration {

    private MetricRegistry metricRegistry;
    private final String host;
    private final int port;

    public GraphiteReporterConfiguration(MetricRegistry metricRegistry, @Value("${graphite.host}") String host, @Value("${graphite.port}") int port) {
        this.metricRegistry = metricRegistry;
        this.host = host;
        this.port = port;
    }

    @Bean
    public Graphite graphiteServer() {
        return new Graphite(new InetSocketAddress(host, port));
    }

    @Bean
    public GraphiteReporter graphiteReporter() {
        GraphiteReporter graphiteReporter = GraphiteReporter.forRegistry(metricRegistry)
                .filter(MetricFilter.ALL)
                .build(graphiteServer());
        graphiteReporter.start(1, TimeUnit.SECONDS);
        return graphiteReporter;
    }
}
