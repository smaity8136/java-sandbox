package com.seedollar.java.sandbox.mockito2.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.seedollar.java.sandbox.mockito2.domain.TaxRequest;
import com.seedollar.java.sandbox.mockito2.enumeration.TaxType;
import com.seedollar.java.sandbox.mockito2.gateway.RevenueGateway;
import com.seedollar.java.sandbox.mockito2.service.TaxService;

@Service
public class TaxServiceImpl implements TaxService {

    private RevenueGateway revenueGateway;

    public TaxServiceImpl(RevenueGateway revenueGateway) {
        this.revenueGateway = revenueGateway;
    }

    @Override
    public boolean isProvisionalTaxPayer(TaxRequest taxRequest) {
        TaxType taxType = revenueGateway.getTaxType(taxRequest.getTaxNumber());
        Objects.requireNonNull(taxRequest, "Tax request cannot be null");
        return taxRequest.getIncome() > 75000 && taxType == TaxType.INDIVIDUAL;
    }
}
