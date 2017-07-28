package za.co.seedollar.sandbox.springamqp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import za.co.seedollar.sandbox.springamqp.configuration.RabbitConfiguration;
import za.co.seedollar.sandbox.springamqp.domain.SalesRepresentative;

/**
 * Created by seedollar on 7/28/17.
 */
@Service
public class SalesRepresentativeMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(SalesRepresentativeMessageListener.class);

    @RabbitListener(queues = RabbitConfiguration.SALES_REPRESENTATIVE_QUEUE)
    public void handleMessage(SalesRepresentative salesRepresentative) {
        logger.info("\nSALES REPRESENTATIVE CONSUMED = " + salesRepresentative);
    }
}
