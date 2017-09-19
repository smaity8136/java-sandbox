package com.seedollar.sandbox.junit5;

import com.seedollar.sandbox.configuration.Junit5Spring4Configuration;
import com.seedollar.sandbox.junit5.domain.PotatoChips;
import com.seedollar.sandbox.junit5.domain.Snack;
import com.seedollar.sandbox.junit5.service.HealthyVendingMachine;
import com.seedollar.sandbox.junit5.service.VendingMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Junit5Spring4Configuration.class)
public class Junit5Spring4Test {

    @Autowired
    VendingMachine generalVendingMachine;

    @Autowired
    VendingMachine healthyVendingMachine;

    @Test
    @DisplayName("Confirm that the spring-test-junit5 module is working by asserting the wired beans")
    public void testjunit5SpringTestWiring() {
        Snack s001 = generalVendingMachine.dispense("S001");
        Assertions.assertEquals(Snack.Color.BROWN, s001.color());

        Assertions.assertFalse(healthyVendingMachine.addSnack(new PotatoChips()));
        Assertions.assertTrue(generalVendingMachine.addSnack(new PotatoChips()));
    }
}
