package com.seedollar.spring.reactive.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by seedollar on 2/22/17.
 */
public class Owner {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private int ranking;

    private List<Pet> pets;

    public Owner(String lastName, List<Pet> pets) {
        this.lastName = lastName;
        this.pets = pets;
    }

    public Owner(String id, String firstName, String lastName, int ranking) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ranking = ranking;
    }

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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
