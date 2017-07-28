package za.co.seedollar.sandbox.springamqp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.seedollar.sandbox.springamqp.domain.Customer;

/**
 * Created by seedollar on 7/28/17.
 */
public class CustomerMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomerMessageListener.class);

    public void handleMessage(Customer customer) {
        logger.info("\nCUSTOMER CONSUMED = " + customer);
    }
}
