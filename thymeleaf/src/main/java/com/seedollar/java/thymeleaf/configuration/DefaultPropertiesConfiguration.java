package com.seedollar.java.thymeleaf.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seedollar on 5/11/16.
 */
@Configuration
@ConfigurationProperties(prefix = "thymeleaf")
public class DefaultPropertiesConfiguration {

    private List<String> defaultFeatures = new ArrayList<>();

    private List<String> defaultItems = new ArrayList<>();

    public List<String> getDefaultFeatures() {
        return defaultFeatures;
    }

    public List<String> getDefaultItems() {
        return defaultItems;
    }

    public void setDefaultItems(List<String> defaultItems) {
        this.defaultItems = defaultItems;
    }
}
