package com.seedollar.java.sandbox.resilience4j.service.impl;

import com.seedollar.java.sandbox.resilience4j.service.PaymentsService;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentsServiceImpl.class);
    private Bulkhead paymentServiceBulkhead;

    public PaymentsServiceImpl(Bulkhead paymentServiceBulkhead) {
        this.paymentServiceBulkhead = paymentServiceBulkhead;
    }

    @Override
    public void payAccount(String source, String target, Double amount) {
        // Decorate bulkhead with function
        CheckedRunnable makePaymentTask = Bulkhead.decorateCheckedRunnable(paymentServiceBulkhead, () -> {
            if (paymentServiceBulkhead.isCallPermitted()) {
                logger.info("Transferring funds: ${} from source account: {} to target account: {}", amount, source, target);
            } else {
                throw new RuntimeException("Bulkhead full");
            }

        });
        Try.run(makePaymentTask).onSuccess(ignore -> paymentServiceBulkhead.onComplete());
    }
}
