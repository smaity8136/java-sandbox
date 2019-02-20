package com.seedollar.java.sandbox.tls.client.rest;

import com.seedollar.java.sandbox.tls.client.model.CarEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarClientController {

    @Value(value = "${ssl.server.host.url}")
    private String sslServerHostUrl;

    private RestTemplate restTemplate;

    public CarClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{carId}")
    public ResponseEntity<String> getTlsCar(@PathVariable("carId") long carId) {
        return restTemplate.getForEntity(sslServerHostUrl + "/cars/" + carId, String.class);
    }

    @PostMapping
    public ResponseEntity<String> addTlsCar(@RequestBody CarEntry carEntry) {
        return restTemplate.postForEntity(sslServerHostUrl+ "/cars", carEntry, String.class);
    }

    @GetMapping
    public ResponseEntity<List> getTlsCars() {
        return restTemplate.getForEntity(sslServerHostUrl + "/cars", List.class);
    }

}
