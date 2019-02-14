package com.seedollar.java.sandbox.http.caching.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {

    private String uid;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private LocalDateTime birthDate;


}
