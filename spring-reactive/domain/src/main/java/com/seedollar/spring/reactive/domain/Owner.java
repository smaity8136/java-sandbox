package com.seedollar.spring.reactive.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by seedollar on 2/22/17.
 */
public class Owner {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private int ranking;

    public String getId() {
        return id;
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
