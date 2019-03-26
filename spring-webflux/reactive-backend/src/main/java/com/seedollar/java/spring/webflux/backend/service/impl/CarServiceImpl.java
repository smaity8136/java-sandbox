package com.seedollar.java.spring.webflux.backend.service.impl;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.consumer.CarConsumer;
import com.seedollar.java.spring.webflux.backend.domain.Car;
import com.seedollar.java.spring.webflux.backend.domain.dto.CarResponse;
import com.seedollar.java.spring.webflux.backend.service.CarService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarServiceImpl implements CarService {

    private CarConsumer carConsumer;
    private Transformer1<CarResponse, Car> carTransformer;

    public CarServiceImpl(CarConsumer carConsumer, Transformer1<CarResponse, Car> carTransformer) {
        this.carConsumer = carConsumer;
        this.carTransformer = carTransformer;
    }

    @Override
    public Mono<Car> getCar(long carId) {
        return carConsumer.getCar(carId).map(carTransformer);
    }

    @Override
    public Flux<Car> getCars() {
        return carConsumer.getCars().map(carTransformer);
    }
}
