package com.seedollar.java.mongodb;

import com.seedollar.java.mongodb.domain.Order;
import com.seedollar.java.mongodb.domain.OrderItem;
import com.seedollar.java.mongodb.domain.User;
import com.seedollar.java.mongodb.enumeration.UserStatusType;
import com.seedollar.java.mongodb.repository.OrderRepository;
import com.seedollar.java.mongodb.repository.UserRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by seedollar on 4/21/16.
 */
@SpringBootApplication
public class MongoRepositoriesMain implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MongoRepositoriesMain.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }


    @Override
    public void run(String... args) throws Exception {

        // Flush the collections before we execute the code below
        userRepository.deleteAll();
        orderRepository.deleteAll();

        userRepository.save(new User("Kevin", "Smith", new Date(), UserStatusType.LOCKED));
        userRepository.save(new User("Lenny", "Durran", new Date(), UserStatusType.INACTIVE));
        userRepository.save(new User("Carmen", "Salson", new Date(), UserStatusType.INACTIVE));
        userRepository.save(new User("Queen", "Antworth", new Date(), UserStatusType.LOCKED));
        userRepository.save(new User("Vera", "Underberg", new Date(), UserStatusType.ACTIVE));

        User vera = userRepository.findByFirstName("Vera");
        Assert.assertEquals(UserStatusType.ACTIVE, vera.getUserStatusType());

        List<User> inactiveUsers = userRepository.findByUserStatusType(UserStatusType.INACTIVE);
        Assert.assertEquals(2, inactiveUsers.size());

        // Save an order and some order items
        Order order = new Order("order Vera", new Date(), false, vera.getId());

        OrderItem orderItem1 = new OrderItem("order Item1", 863.23, "Sony");
        OrderItem orderItem2 = new OrderItem("order Item2", 979.97, "Microsoft");
        OrderItem orderItem3 = new OrderItem("order Item3", 34.10, "Cola");
        OrderItem orderItem4 = new OrderItem("order Item4", 2.99, "Nike");

        order.setOrderItems(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));
        orderRepository.save(order);

        // Lookup the order id and print out the orderitems
        Order retrievedOrder = orderRepository.findOne(order.getId());

        retrievedOrder.getOrderItems().stream().forEach(oi -> System.out.println("oi = " + oi.getItemName()));
    }
}
