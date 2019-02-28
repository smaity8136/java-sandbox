package com.seedollar.java.sandbox.resilience4j.config;

import com.codahale.metrics.servlets.AdminServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean dropwizardAdminServlet() {
        ServletRegistrationBean dropwizardAdminServletRegistrationBean = new ServletRegistrationBean(new AdminServlet(), "/dropwizardAdmin/*");
        dropwizardAdminServletRegistrationBean.setLoadOnStartup(1);
        return dropwizardAdminServletRegistrationBean;
    }
}
