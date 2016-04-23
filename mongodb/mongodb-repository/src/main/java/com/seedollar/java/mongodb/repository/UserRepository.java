package com.seedollar.java.mongodb.repository;

import com.seedollar.java.mongodb.domain.User;
import com.seedollar.java.mongodb.enumeration.UserStatusType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by seedollar on 4/21/16.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByFirstName(String firstName);

    List<User> findByUserStatusType(UserStatusType userStatusType);
}
