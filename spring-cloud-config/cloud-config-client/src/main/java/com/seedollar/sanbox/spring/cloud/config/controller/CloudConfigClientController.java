package com.seedollar.sanbox.spring.cloud.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class CloudConfigClientController {

    @Value("${director: Gordan}")
    private String directorName;


    @GetMapping("/director")
    public String getDirectorName() {
        return directorName;
    }
}
