package com.seedollar.java.spring.webflux.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class LicensingRouterFunctionConfiguration {

    @Bean
    public RouterFunction<?> licensingRoutes() {
        return RouterFunctions.route()
                .GET("/licensing/{ssn}", request -> (ThreadLocalRandom.current().nextBoolean()) ? ServerResponse.ok().syncBody(true) : ServerResponse.ok().syncBody(false))
                .filter((request, next) -> {
                    Duration duration = Duration.ofMillis(1500);
                    return Mono.delay(duration).flatMap(aLong -> next.handle(request));
                })
                .build();
    }
}
