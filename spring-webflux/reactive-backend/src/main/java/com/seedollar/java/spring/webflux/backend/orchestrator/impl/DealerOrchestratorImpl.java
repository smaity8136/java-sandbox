package com.seedollar.java.spring.webflux.backend.orchestrator.impl;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer2;
import com.seedollar.java.spring.webflux.backend.domain.Accessory;
import com.seedollar.java.spring.webflux.backend.domain.Car;
import com.seedollar.java.spring.webflux.backend.domain.OTP;
import com.seedollar.java.spring.webflux.backend.domain.Quote;
import com.seedollar.java.spring.webflux.backend.orchestrator.DealerOrchestrator;
import com.seedollar.java.spring.webflux.backend.service.AccessoryService;
import com.seedollar.java.spring.webflux.backend.service.CarService;
import com.seedollar.java.spring.webflux.backend.service.LicensingService;
import com.seedollar.java.spring.webflux.backend.service.QuoteService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DealerOrchestratorImpl implements DealerOrchestrator {

  private final CarService carService;
  private final AccessoryService accessoryService;
  private final QuoteService quoteService;
  private LicensingService licensingService;
  private Transformer2<Car, Quote, OTP> otpTransformer;

  public DealerOrchestratorImpl(CarService carService, AccessoryService accessoryService,
      QuoteService quoteService, LicensingService licensingService,
      Transformer2<Car, Quote, OTP> otpTransformer) {
    this.carService = carService;
    this.accessoryService = accessoryService;
    this.quoteService = quoteService;
    this.licensingService = licensingService;
    this.otpTransformer = otpTransformer;
  }

  @Override
  public Mono<OTP> generateOTP(long carId) {
    return carService.getCar(carId)
        .flatMap(car -> {
          log.debug("Car Id: {}", car.getCarId());
          log.debug("Car purchasePrice: {}", car.getPurchasePrice());

          List<Mono<Accessory>> monoList = car.getAccessoryIds().stream()
              .map(accessoryService::getAccessory).collect(Collectors.toList());
          Mono<Quote> quoteMono = quoteService.getQuote(car.getPurchasePrice());
          Mono<Boolean> licenseMono = licensingService
              .driverHasValidLicense(UUID.randomUUID().toString());

          Mono<Car> zipCarMono = Mono.zip(monoList, args -> {
            Arrays.stream(args).map(arg -> (Accessory) arg)
                .map(accessory -> car.getAccessories().add(accessory));
            return car;
          });
          return Mono.zip(zipCarMono, quoteMono, licenseMono)
              .map(tuple3 -> otpTransformer.apply(tuple3.getT1(), tuple3.getT2()));
        }).subscriberContext(context -> {
          log.info("DealerOrchestrationImpl: " + context.get("correlationId"));
          return context;
        });
  }
}
