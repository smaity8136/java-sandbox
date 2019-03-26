package com.seedollar.java.spring.webflux.backend.service.transformer;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.domain.Car;
import com.seedollar.java.spring.webflux.backend.domain.dto.CarResponse;
import org.springframework.stereotype.Component;

@Component
public class CarTransformer implements Transformer1<CarResponse, Car> {

    @Override
    public Car apply(CarResponse carResponse) {
        return Car.builder()
                .carId(carResponse.getCarId())
                .isElectric(carResponse.isElectric())
                .make(carResponse.getMake())
                .model(carResponse.getModel())
                .plate(carResponse.getPlate())
                .purchasePrice(carResponse.getPurchasePrice())
                .accessoryIds(carResponse.getAccessoryIds()).build();
    }
}
