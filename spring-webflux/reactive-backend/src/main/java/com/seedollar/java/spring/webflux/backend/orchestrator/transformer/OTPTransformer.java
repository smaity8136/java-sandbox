package com.seedollar.java.spring.webflux.backend.orchestrator.transformer;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer2;
import com.seedollar.java.spring.webflux.backend.domain.Accessory;
import com.seedollar.java.spring.webflux.backend.domain.Car;
import com.seedollar.java.spring.webflux.backend.domain.OTP;
import com.seedollar.java.spring.webflux.backend.domain.Quote;
import org.springframework.stereotype.Component;

@Component
public class OTPTransformer implements Transformer2<Car, Quote, OTP> {

    @Override
    public OTP apply(Car car, Quote quote) {
        return OTP.builder().car(car).quote(quote).totalAmount(calculateTotalAmount(car, quote)).build();
    }

    private double calculateTotalAmount(Car car, Quote quote) {
        double totalAmount = 0.0;
//        double totalAccessories = car.getAccessories().stream().map(Accessory::getCost).reduce(0.0, (a, b) -> a + b, (a, b) -> a);
//        totalAmount += quote.getPremium();
//        totalAmount += totalAccessories;
        totalAmount += car.getPurchasePrice();
        return totalAmount;
    }
}
