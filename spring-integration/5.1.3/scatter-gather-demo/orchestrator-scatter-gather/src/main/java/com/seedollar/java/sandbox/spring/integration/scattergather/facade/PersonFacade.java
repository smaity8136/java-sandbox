package com.seedollar.java.sandbox.spring.integration.scattergather.facade;

import com.seedollar.java.sandbox.spring.integration.scattergather.domain.Person;

public interface PersonFacade {

    Person getPerson(String personId);
}
