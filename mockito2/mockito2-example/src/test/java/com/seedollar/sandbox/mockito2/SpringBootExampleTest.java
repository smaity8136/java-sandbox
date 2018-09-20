package com.seedollar.sandbox.mockito2;

import com.seedollar.java.sandbox.mockito2.Mockito2SpringBootApplication;
import com.seedollar.java.sandbox.mockito2.domain.TaxRequest;
import com.seedollar.java.sandbox.mockito2.service.TaxService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Mockito2SpringBootApplication.class)
public class SpringBootExampleTest {

    @Autowired
    private TaxService taxService;

    @Test
    @DisplayName("Test to show spring boot wiring")
    public void testIsProvisional() {
        TaxRequest taxRequest = new TaxRequest("458502183882083BCX");
        taxRequest.setIncome(97600d);
        taxService.isProvisionalTaxPayer(taxRequest);
    }

}
