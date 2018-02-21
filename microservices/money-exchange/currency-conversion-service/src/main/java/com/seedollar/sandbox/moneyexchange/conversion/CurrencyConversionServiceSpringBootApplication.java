package com.seedollar.sandbox.moneyexchange.conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.seedollar.sandbox.moneyexchange.conversion.proxy")
@EnableDiscoveryClient
public class CurrencyConversionServiceSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionServiceSpringBootApplication.class, args);
    }
}
