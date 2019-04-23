package com.seedollar.java.spring.webflux.backend.service.impl;

import com.seedollar.java.spring.webflux.backend.common.util.MDCLoggingUtil;
import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.consumer.CarConsumer;
import com.seedollar.java.spring.webflux.backend.domain.Car;
import com.seedollar.java.spring.webflux.backend.domain.dto.CarResponse;
import com.seedollar.java.spring.webflux.backend.service.CarService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    private CarConsumer carConsumer;
    private Transformer1<CarResponse, Car> carTransformer;

    public CarServiceImpl(CarConsumer carConsumer, Transformer1<CarResponse, Car> carTransformer) {
        this.carConsumer = carConsumer;
        this.carTransformer = carTransformer;
    }

    @Override
    public Mono<Car> getCar(long carId) {
        return carConsumer.getCar(carId).map(carTransformer)
            .doOnEach( MDCLoggingUtil.logOnNext(accessory -> log.info("Retrieving car for CarId: {}", carId)));

    }

    @Override
    public Flux<Car> getCars() {
        return carConsumer.getCars().map(carTransformer);
    }
}
