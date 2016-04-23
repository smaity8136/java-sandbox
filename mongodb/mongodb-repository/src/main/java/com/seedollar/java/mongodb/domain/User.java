package com.seedollar.java.mongodb.domain;

import com.seedollar.java.mongodb.enumeration.UserStatusType;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by seedollar on 4/21/16.
 */
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private UserStatusType userStatusType;

    private Date createdDate;

    private Double rating;

    public User(String firstName, String lastName, Date createdDate, UserStatusType userStatusType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.userStatusType = userStatusType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserStatusType getUserStatusType() {
        return userStatusType;
    }

    public void setUserStatusType(UserStatusType userStatusType) {
        this.userStatusType = userStatusType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
