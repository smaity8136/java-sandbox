package com.seedollar.sandbox.mockito2;

import com.seedollar.java.sandbox.mockito2.enumeration.TaxType;
import com.seedollar.java.sandbox.mockito2.domain.TaxRequest;
import com.seedollar.java.sandbox.mockito2.service.TaxService;
import com.seedollar.java.sandbox.mockito2.service.impl.TaxServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.seedollar.java.sandbox.mockito2.gateway.RevenueGateway;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito2 Junit test extension example")
public class Mockito2ExampleTest {

    @Mock
    private RevenueGateway revenueGateway;

    private TaxService taxService;

    @BeforeEach
    public void setUp() {
        taxService = new TaxServiceImpl(revenueGateway);
    }

    @Test
    @DisplayName("Test for ZARevenueGateway to determine tax type based on tax number")
    public void testCanadaTaxServiceIsProvisional() {
        TaxRequest taxRequest = mock(TaxRequest.class);
        when(taxRequest.getTaxNumber()).thenReturn(UUID.randomUUID().toString());
        when(taxRequest.getIncome()).thenReturn(82000d);
        when(revenueGateway.getTaxType(anyString())).thenReturn(TaxType.INDIVIDUAL);
        Assert.assertTrue(taxService.isProvisionalTaxPayer(taxRequest));
    }





}
