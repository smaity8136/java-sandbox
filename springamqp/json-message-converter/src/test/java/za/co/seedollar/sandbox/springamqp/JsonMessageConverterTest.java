package za.co.seedollar.sandbox.springamqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.seedollar.sandbox.springamqp.configuration.ConsumerConfiguration;
import za.co.seedollar.sandbox.springamqp.configuration.RabbitConfiguration;
import za.co.seedollar.sandbox.springamqp.domain.Customer;
import za.co.seedollar.sandbox.springamqp.domain.SalesRepresentative;

import java.util.Date;

/**
 * Created by seedollar on 7/28/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ConsumerConfiguration.class})
public class JsonMessageConverterTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testJsonMessageConverter() throws InterruptedException {
        Customer testCustomer1 = new Customer("Gavin", "Platinum", new Date());
        Customer testCustomer2 = new Customer("Ruben", "Gold", new Date());
        Customer testCustomer3 = new Customer("Seth", "Silver", new Date());
        SalesRepresentative testSalesRep1 = new SalesRepresentative(5, "ANT Inc", 50000.00d);
        SalesRepresentative testSalesRep2 = new SalesRepresentative(2, "RandCorp", 70000.00d);
        SalesRepresentative testSalesRep3 = new SalesRepresentative(6, "ANT Inc", 63000.00d);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.CUSTOMER_ROUTING_KEY, testCustomer1);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.SALES_REPRESENTATIVE_ROUTING_KEY, testSalesRep1);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.CUSTOMER_ROUTING_KEY, testCustomer2);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.CUSTOMER_ROUTING_KEY, testCustomer3);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.SALES_REPRESENTATIVE_ROUTING_KEY, testSalesRep2);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.SALES_REPRESENTATIVE_ROUTING_KEY, testSalesRep3);

        Thread.sleep(5000);
    }
}
