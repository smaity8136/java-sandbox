package com.seedollar.java.sandbox.mockito2;

import com.seedollar.java.sandbox.mockito2.domain.TaxRequest;
import com.seedollar.java.sandbox.mockito2.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Mockito2SpringBootApplication implements CommandLineRunner {

    @Autowired
    private TaxService taxService;

    public static void main(String[] args) {
        SpringApplication.run(Mockito2SpringBootApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        TaxRequest taxRequest = new TaxRequest("679973628494");
        taxRequest.setIncome(199400d);
        System.out.println("is Provisional = " + taxService.isProvisionalTaxPayer(taxRequest));
    }
}
