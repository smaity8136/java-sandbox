package com.seedollar.java.springboot.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by seedollar on 2016/02/11.
 */
@Component
@ConfigurationProperties(prefix = "developer")
public class DeveloperConfigSettings extends ConfigSettings {
}
