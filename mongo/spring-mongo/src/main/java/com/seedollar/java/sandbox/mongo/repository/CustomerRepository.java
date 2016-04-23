package com.seedollar.java.sandbox.mongo.repository;

import com.seedollar.java.sandbox.mongo.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by seedollar on 4/17/16.
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
