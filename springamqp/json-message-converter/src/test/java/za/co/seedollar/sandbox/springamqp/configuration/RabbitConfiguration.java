package za.co.seedollar.sandbox.springamqp.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by seedollar on 7/28/17.
 */
@EnableRabbit
@PropertySource("classpath:application.properties")
public class RabbitConfiguration {

    public static final String EXCHANGE = "test-exchange";
    public static final String CUSTOMER_QUEUE = "customer-queue";
    public static final String SALES_REPRESENTATIVE_QUEUE = "sales-representative-queue";
    public static final String CUSTOMER_ROUTING_KEY = "customer";
    public static final String SALES_REPRESENTATIVE_ROUTING_KEY = "salesRep";

    @Autowired
    Environment environment;

    @Bean
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE, false, true);
    }

    @Bean
    public Queue customerQueue() {
        return new Queue(CUSTOMER_QUEUE, false, false, true);
    }

    @Bean
    public Queue salesRepresentativeQueue() {
        return new Queue(SALES_REPRESENTATIVE_QUEUE, false, false, true);
    }

    @Bean
    public Binding bindCustomerQueueToExchange() {
        return BindingBuilder.bind(customerQueue()).to(exchange()).with(CUSTOMER_ROUTING_KEY).noargs();
    }

    @Bean
    public Binding bindSalesRepresentativeQueueToExchange() {
        return BindingBuilder.bind(salesRepresentativeQueue()).to(exchange()).with(SALES_REPRESENTATIVE_ROUTING_KEY).noargs();
    }

    @Bean
    public ConnectionFactory rabbitMQConnectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(environment.getProperty("rabbitmq.host"));
        connectionFactory.setUsername(environment.getProperty("rabbitmq.username").trim());
        connectionFactory.setPassword(environment.getProperty("rabbitmq.password").toString());
        connectionFactory.setPort(Integer.parseInt(environment.getProperty("rabbitmq.port")));

        // Check if an virtualhost property is set, and if so we configure the factory accordingly
        String vhost = environment.getProperty("rabbitmq.virtualhost");
        if (vhost != null && !vhost.isEmpty()) {
            connectionFactory.setVirtualHost(vhost);
        }
        return connectionFactory;
    }

    /**
     * This is used by the @RabbitListener annotation. We also set the message converter so that the JSON string payloads are converted to their Java object.
     *
     * @return
     */
    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(rabbitMQConnectionFactory());
        simpleRabbitListenerContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public AmqpAdmin rabbitAdmin() {
        return new RabbitAdmin(rabbitMQConnectionFactory());
    }

    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitMQConnectionFactory());
        // We set the message converter here so that POJOS will automatically converted to JSON when producing messages.
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
