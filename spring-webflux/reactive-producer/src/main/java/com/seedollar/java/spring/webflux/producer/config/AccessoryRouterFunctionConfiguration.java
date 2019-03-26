package com.seedollar.java.spring.webflux.producer.config;

import com.seedollar.java.spring.webflux.producer.domain.Accessory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Configuration
public class AccessoryRouterFunctionConfiguration {

    private static Map<Long, Accessory> ACCESSORIES_MAP = Map.of(
            1L, Accessory.builder().description("Electric Windows").cost(250.54d).build(),
            2L, Accessory.builder().description("Sunroof").cost(740.00d).build(),
            3L, Accessory.builder().description("Tinted Windows").cost(50.80d).build(),
            4L, Accessory.builder().description("21 Alloys").cost(6770.00d).build(),
            5L, Accessory.builder().description("Heated Seats").cost(355.50d).build(),
            6L, Accessory.builder().description("Childproof Locks").cost(29.99d).build(),
            7L, Accessory.builder().description("Leather Seats").cost(1700.00d).build(),
            8L, Accessory.builder().description("Cup Holders").cost(89.00d).build());

    @Bean
    public RouterFunction<?> accessoryRoutes() {
        return RouterFunctions.route()
                .GET("/accessories/{accessoryId}", request -> {
                    Long accessoryId = Long.parseLong(request.pathVariable("accessoryId"));
                    return Optional.ofNullable(ACCESSORIES_MAP.get(accessoryId)).map(accessory -> ServerResponse.ok().syncBody(accessory)).orElse(ServerResponse.notFound().build());

                })
                .GET("/accessories", request -> Optional.ofNullable(ACCESSORIES_MAP.values()).map(accessories -> ServerResponse.ok().syncBody(accessories)).orElse(ServerResponse.notFound().build()))
                .filter((request, next) -> {
                    Duration duration = Duration.ofSeconds(1);
                    return Mono.delay(duration).flatMap(aLong -> next.handle(request));
                })
                .build();
    }
}
