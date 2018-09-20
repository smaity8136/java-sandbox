package com.seedollar.java.sandbox.mockito2.gateway;

import com.seedollar.java.sandbox.mockito2.enumeration.TaxType;
public interface RevenueGateway {

    TaxType getTaxType(String taxNumber);
}
