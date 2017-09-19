package com.seedollar.sandbox.configuration;

import com.seedollar.sandbox.junit5.domain.*;
import com.seedollar.sandbox.junit5.service.GeneralVendingMachine;
import com.seedollar.sandbox.junit5.service.HealthyVendingMachine;
import com.seedollar.sandbox.junit5.service.VendingMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Junit5Spring4Configuration {

    @Bean
    public VendingMachine generalVendingMachine() {
        return new GeneralVendingMachine(new Pear(), new Apple(), new ChocolateBar(), new PotatoChips());
    }

    @Bean
    public VendingMachine healthyVendingMachine() {
        return new HealthyVendingMachine(new Apple(), new Banana(), new Pear());
    }

}
