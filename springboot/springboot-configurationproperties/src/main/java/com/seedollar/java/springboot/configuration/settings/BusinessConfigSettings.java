package com.seedollar.java.springboot.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by seedollar on 2016/02/11.
 */
@Component
@ConfigurationProperties(prefix = "business")
public class BusinessConfigSettings extends ConfigSettings {
}
