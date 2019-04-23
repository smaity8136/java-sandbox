package com.seedollar.java.spring.webflux.producer.config;

import com.seedollar.java.spring.webflux.producer.domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class CarRouterFunctionConfiguration {

    private static Map<Long, Car> CAR_MAP = Map.of(
            1L, Car.builder().carId(1L).make("Audi").model("A6").plate("GHC-342-ON").isElectric(false).purchasePrice(195000.00)
                    .workshopIds(List.of(11L, 12L, 14L))
                    .accessoryIds(List.of(1L, 2L, 4L, 5L, 8L)).build(),
            2L, Car.builder().carId(2L).make("Audi").model("S3").plate("HJS-697-ON").isElectric(false).purchasePrice(65000.00)
                    .workshopIds(List.of(14L, 15L))
                    .accessoryIds(List.of(2L, 3L, 4L, 7L)).build(),
            3L, Car.builder().carId(3L).make("BMW").model("I8").plate("JSN-612-BC").isElectric(true).purchasePrice(359999.00)
                    .workshopIds(List.of(13L))
                    .accessoryIds(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L)).build(),
            4L, Car.builder().carId(4L).make("Ford").model("Mustang").plate("VHJ-286-MB").isElectric(false).purchasePrice(92000.50)
                    .workshopIds(List.of(12L, 13L, 14L, 15L))
                    .accessoryIds(List.of(1L, 2L, 3L, 6L, 8L)).build(),
            5L, Car.builder().carId(5L).make("Toyota").model("Corolla").plate("SDO-997-ON").isElectric(false).purchasePrice(42590.40)
                    .workshopIds(List.of(11L, 12L, 13L, 14L, 15L))
                    .accessoryIds(List.of(3L, 5L, 7L, 8L)).build(),
            6L, Car.builder().carId(6L).make("Tesla").model("Model X").plate("KJL-581-BC").isElectric(true).purchasePrice(250000.00)
                    .workshopIds(List.of(12L, 14L))
                    .accessoryIds(List.of(1L, 2L, 3L, 4L, 5L, 6L, 8L)).build());

    @Bean
    public RouterFunction<?> carRoutes() {
        return RouterFunctions.route()
                .GET("/cars/{carId}", request -> {
                    Long carId = Long.parseLong(request.pathVariable("carId"));
                    return Optional.ofNullable(CAR_MAP.get(carId)).map(car -> ServerResponse.ok().syncBody(car)).orElse(ServerResponse.notFound().build());
                })
                .GET("/cars", request -> Optional.ofNullable(CAR_MAP.values()).map(cars -> ServerResponse.ok().syncBody(cars)).orElse(ServerResponse.notFound().build()))
                .filter((request, next) -> {
                    Duration duration = Duration.ofSeconds(2);
                    return Mono.delay(duration).flatMap(aLong -> next.handle(request));
                })
                .build();
    }

}
