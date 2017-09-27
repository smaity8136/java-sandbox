package com.seedollar.sandbox.springcore.configuration;

import com.seedollar.sandbox.springcore.aspect.DrivingAspect;
import com.seedollar.sandbox.springcore.aspect.GearTrackingAspect;
import com.seedollar.sandbox.springcore.aspect.ParkingIntroducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SpringAOPConfiguration {

    @Bean
    public DrivingAspect drivingAspect() {
        return new DrivingAspect();
    }

    @Bean
    public GearTrackingAspect gearTrackingAspect() {
        return new GearTrackingAspect();
    }

    @Bean
    public ParkingIntroducer parkingIntroducer() {
        return new ParkingIntroducer();
    }
}
