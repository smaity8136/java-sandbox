package com.seedollar.java.spring.webflux.producer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class RemoteWebfluxProducerTest {

    private WebTestClient webTestClient = WebTestClient.bindToServer().build();

    @Test
    @DisplayName("Test that the get car endpoint returns a valid car response and http 200")
    public void testGetCar() {
        webTestClient.get().uri("http://localhost:8080/cars/{carId}", 2).exchange()
                .expectStatus().isOk();
    }
}
