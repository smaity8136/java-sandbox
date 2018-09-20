package com.seedollar.java.sandbox.mockito2.service;

import com.seedollar.java.sandbox.mockito2.domain.TaxRequest;

public interface TaxService {

    boolean isProvisionalTaxPayer(TaxRequest taxRequest);
}
