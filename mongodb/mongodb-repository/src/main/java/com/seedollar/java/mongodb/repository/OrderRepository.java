package com.seedollar.java.mongodb.repository;

import com.seedollar.java.mongodb.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by seedollar on 4/21/16.
 */
public interface OrderRepository extends MongoRepository<Order, String> {

}
