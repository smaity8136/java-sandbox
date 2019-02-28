package com.seedollar.java.sandbox.resilience4j.listener;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;

@WebListener
public class DropwizardMetricServletContextListener extends MetricsServlet.ContextListener {

    @Autowired
    private MetricRegistry metricRegistry;

    @Override
    protected MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }
}
