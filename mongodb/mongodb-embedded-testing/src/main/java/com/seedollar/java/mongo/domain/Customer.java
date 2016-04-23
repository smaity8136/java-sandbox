package com.seedollar.java.mongo.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by seedollar on 4/22/16.
 */
public class Customer {

    @Id
    private String id;

    private String name;

    private String idNumber;

    private String dateOfBirth;

    private List<Address> addresses;

    public Customer(String name, String idNumber, String dateOfBirth) {
        this.name = name;
        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
