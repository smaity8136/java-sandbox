package com.seedollar.java.sandbox.spring.integration.scattergather.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Person {

    private String name;
    private int age;
    private String gender;
    private LocalDate birthDate;
    private CapuletAttributes capuletAttributes;
    private MontagueAttributes montagueAttributes;
    private long duration;



}
