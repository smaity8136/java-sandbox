package com.seedollar.java.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by seedollar on 2016/01/26.
 */
@RestController
@EnableAutoConfiguration
public class SpringBootMain {

    @RequestMapping("/")
    public String greet() {
        return "SpringBoot Greeter";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }


}
