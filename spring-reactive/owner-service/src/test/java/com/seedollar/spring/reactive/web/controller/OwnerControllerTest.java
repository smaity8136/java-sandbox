package com.seedollar.spring.reactive.web.controller;

import com.seedollar.spring.reactive.domain.Owner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by seedollar on 3/3/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OwnerControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(OwnerControllerTest.class);

    private WebClient webClient;

    @LocalServerPort
    private int port;

    @Before
    public void init() {
        this.webClient = WebClient.create("http://localhost:" + this.port);
    }

    @Test
    public void testAddOwner() {
        Owner newOwner = new Owner(null, "Cravigon", "Latsien", 597);
        newOwner = webClient.post().uri("/owner").accept(MediaType.APPLICATION_JSON).exchange(BodyInserters.fromObject(newOwner))
                .then(response -> response.bodyToMono(Owner.class)).block();
        logger.info("Owner saved: " + newOwner);
    }
}
