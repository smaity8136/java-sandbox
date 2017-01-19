package com.seedollar.java.spring.retry;

import com.seedollar.java.spring.retry.configuration.RetryTemplateConfiguration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by seedollar on 1/19/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RetryTemplateConfiguration.class})
public class SpringRetryTemplateTest {

    private static final Logger logger = LoggerFactory.getLogger(SpringRetryTemplateTest.class);

    @Autowired
    RetryTemplate retryTemplate;

    @Test
    public void testRetryTemplate_Success() {
        String result = retryTemplate.execute(new RetryCallback<String, RuntimeException>() {
            CountDownLatch latch = new CountDownLatch(2);

            @Override
            public String doWithRetry(RetryContext context) throws RuntimeException {
                latch.countDown();
                if (latch.getCount() == 1) {
                    throw new RuntimeException("Mock Failure");
                }
                return "retryTemplateSuccess";
            }
        });

        Assertions.assertEquals("retryTemplateSuccess", result);
    }

    @Test(expected = RuntimeException.class)
    public void testRetryTemplate_Fail_Timeout() {
        AtomicInteger counter = new AtomicInteger(0);
        // The retry template will keep trying until the retry policy times out (10 seconds)
        retryTemplate.execute((arg) -> {
            logger.info(String.format("Retrying attempt %d....", counter.incrementAndGet()));
            throw new RuntimeException();
        });
    }
}
