package com.seedollar.java.spring.webflux.backend.service;

import com.seedollar.java.spring.webflux.backend.domain.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarService {

    Mono<Car> getCar(long carId);

    Flux<Car> getCars();
}
