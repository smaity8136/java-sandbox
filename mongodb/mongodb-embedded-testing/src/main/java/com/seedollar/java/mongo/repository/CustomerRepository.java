package com.seedollar.java.mongo.repository;

import com.seedollar.java.mongo.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by seedollar on 4/22/16.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByIdNumber(String idNumber);

    List<Customer> findByName(String name);
}
