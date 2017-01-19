package com.seedollar.java.spring.retry;

import com.seedollar.java.spring.retry.service.CalculationService;
import javaslang.control.Try;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * Created by seedollar on 1/19/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RetryConfiguration.class})
public class SpringRetryTest {

    @Autowired
    CalculationService calculationService;

    @Test
    public void testRetry() {
        Assertions.assertEquals(600397.45d, calculationService.calculateRetry(), .001);
    }

    @Test
    public void testRetryExhaustion() {
        Assertions.assertThrows(ExhaustedRetryException.class, () -> calculationService.calculateRetryExclusion());
    }

    @Test
    public void testRetryAndRecoverSQL() throws SQLException {
        Assertions.assertEquals("recoveryForSQL", calculationService.calculateAndRecoverSQL());
    }

    @Test
    public void testRetryAndRecoverNullPointer() throws SQLException {
        Assertions.assertEquals("recoveryForNullPointer", calculationService.calculateAndRecoverNullPointer());
    }
}
