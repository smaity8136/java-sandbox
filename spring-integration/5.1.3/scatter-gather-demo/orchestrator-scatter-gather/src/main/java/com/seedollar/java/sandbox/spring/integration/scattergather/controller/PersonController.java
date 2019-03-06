package com.seedollar.java.sandbox.spring.integration.scattergather.controller;

import com.seedollar.java.sandbox.spring.integration.scattergather.domain.Person;
import com.seedollar.java.sandbox.spring.integration.scattergather.facade.PersonFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonFacade personFacade;

    public PersonController(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") String personId) {
        StopWatch watch = new StopWatch("testGetPerson");

        return ResponseEntity.ok(personFacade.getPerson(personId));
    }
}
