package com.seedollar.java.sandbox.tls.server.controller;

import com.seedollar.java.sandbox.tls.server.model.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/cars")
public class CarController {

    private Map<Long, Car> cars = new HashMap<>();

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable("carId") long carId) {
        return Optional.ofNullable(ResponseEntity.ok(cars.get(carId))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> addNewCar(@RequestBody Car newCar) {
        if (newCar.getCarId() == null) {
            newCar.setCarId(ThreadLocalRandom.current().nextLong());
        }
        cars.put(newCar.getCarId(), newCar);
        return ResponseEntity.ok("New Car Added Successfully");
    }

    @GetMapping
    public ResponseEntity<Collection> getAllCars() {
        return ResponseEntity.ok(cars.values());
    }

}
