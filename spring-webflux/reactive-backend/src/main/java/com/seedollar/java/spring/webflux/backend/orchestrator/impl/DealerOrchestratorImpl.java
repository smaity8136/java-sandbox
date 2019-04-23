package com.seedollar.java.spring.webflux.backend.orchestrator.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.seedollar.java.spring.webflux.backend.common.util.MDCLoggingUtil;
import com.seedollar.java.spring.webflux.backend.common.util.Transformer2;
import com.seedollar.java.spring.webflux.backend.domain.Accessory;
import com.seedollar.java.spring.webflux.backend.domain.Car;
import com.seedollar.java.spring.webflux.backend.domain.OTP;
import com.seedollar.java.spring.webflux.backend.domain.Quote;
import com.seedollar.java.spring.webflux.backend.domain.Workshop;
import com.seedollar.java.spring.webflux.backend.orchestrator.DealerOrchestrator;
import com.seedollar.java.spring.webflux.backend.service.AccessoryService;
import com.seedollar.java.spring.webflux.backend.service.CarService;
import com.seedollar.java.spring.webflux.backend.service.LicensingService;
import com.seedollar.java.spring.webflux.backend.service.QuoteService;
import com.seedollar.java.spring.webflux.backend.service.WorkshopService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class DealerOrchestratorImpl implements DealerOrchestrator {

  private final CarService carService;
  private final AccessoryService accessoryService;
  private final QuoteService quoteService;
  private LicensingService licensingService;
  private WorkshopService workshopService;
  private Transformer2<Car, Quote, OTP> otpTransformer;

  public DealerOrchestratorImpl(CarService carService, AccessoryService accessoryService,
      QuoteService quoteService, LicensingService licensingService, WorkshopService workshopService,
      Transformer2<Car, Quote, OTP> otpTransformer) {
    this.carService = carService;
    this.accessoryService = accessoryService;
    this.quoteService = quoteService;
    this.licensingService = licensingService;
    this.workshopService = workshopService;
    this.otpTransformer = otpTransformer;
  }

  @Override
  public Mono<OTP> generateOTP(long carId) {
    return carService.getCar(carId)
        .flatMap(car -> {
          log.debug("Car Id: {}", car.getCarId());
          log.debug("Car purchasePrice: {}", car.getPurchasePrice());

          List<Mono<Accessory>> monoAccessoryList = car.getAccessoryIds().stream()
              .map(accessoryService::getAccessory).collect(Collectors.toList());
          List<Mono<Workshop>> monoWorkshopList = car.getWorkshopIds().stream()
              .map(workshopService::getWorkshop).collect(Collectors.toList());
          Mono<Quote> quoteMono = quoteService.getQuote(car.getPurchasePrice());

          Mono<Boolean> licenseMono = licensingService
              .driverHasValidLicense(UUID.randomUUID().toString());

          Mono<Car> zipCarMono1 = Mono.zip(monoAccessoryList, args -> {
            List<Accessory> accessories = Arrays.stream(args).map(arg -> (Accessory) arg)
                .collect(Collectors.toList());
            car.setAccessories(accessories);
            return car;
          });

          Mono<Car> zipCarMono2 = Mono.zip(monoWorkshopList, args -> {
            List<Workshop> workshops = Arrays.stream(args).map(arg -> (Workshop) arg)
                .collect(Collectors.toList());
            car.setWorkshops(workshops);
            return car;
          });

          Mono<Car> carMono = zipCarMono1.zipWith(zipCarMono2, (a, b) -> {
            a.setWorkshops(b.getWorkshops());
            return a;
          });

          return Mono.zip(carMono, quoteMono, licenseMono)
              .map(tuple3 -> otpTransformer.apply(tuple3.getT1(), tuple3.getT2()));
        })
        .doOnEach(MDCLoggingUtil.logOnNext(
            otp -> log.info("Dealer Orchestrator merging for carId {}", otp.getCar().getCarId())));

  }
}
