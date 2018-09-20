package com.seedollar.java.sandbox.mockito2.gateway.impl;

import com.seedollar.java.sandbox.mockito2.enumeration.TaxType;
import com.seedollar.java.sandbox.mockito2.gateway.RevenueGateway;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ZARevenueGateway implements RevenueGateway {
    @Override
    public TaxType getTaxType(String taxNumber) {
        Objects.requireNonNull(taxNumber, "tax number cannot be null");
        if (taxNumber.matches("^\\d{12}$")) {
            return TaxType.INDIVIDUAL;
        } else if (taxNumber.matches("^\\d{15}$")) {
            return TaxType.COMPANY;
        } else if (taxNumber.matches("^\\d{16}+[a-zA-Z]{3}$")){
            return TaxType.TRUST;
        } else {
            throw new IllegalArgumentException("This tax number is invalid");
        }
    }
}
