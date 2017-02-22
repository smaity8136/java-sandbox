package com.seedollar.spring.reactive.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * Created by seedollar on 2/22/17.
 */
public class Pet {

    @Id
    private String id;
    private String ownerId;
    private String name;
    private String breed;
    private int age;
    private LocalDate birthDate;

    public Pet(String id, String ownerId, String name, String breed, int age, LocalDate birthDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
