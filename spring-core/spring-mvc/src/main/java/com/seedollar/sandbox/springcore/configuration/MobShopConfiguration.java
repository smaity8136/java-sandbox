package com.seedollar.sandbox.springcore.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {PersistenceConfiguration.class, ServiceConfiguration.class})
public class MobShopConfiguration {
}
