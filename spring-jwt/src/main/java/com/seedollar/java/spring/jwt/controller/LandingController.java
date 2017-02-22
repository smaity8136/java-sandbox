package com.seedollar.java.spring.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by seedollar on 2/21/17.
 */
@RestController
@RequestMapping("/secure")
public class LandingController {

    @GetMapping(value = "/landing", produces = "text/plain")
    public String landingPage() {
        return "Landing page. You have access";
    }
}
