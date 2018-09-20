package com.seedollar.sandbox.docker.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/greeting")
    public String greetingResponse() {
        return "Greeting example response";
    }
}
