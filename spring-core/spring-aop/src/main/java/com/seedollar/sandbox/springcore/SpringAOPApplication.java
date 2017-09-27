package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.aspect.GearTrackingAspect;
import com.seedollar.sandbox.springcore.configuration.SpringAOPConfiguration;
import com.seedollar.sandbox.springcore.domain.Car;
import com.seedollar.sandbox.springcore.domain.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAOPApplication implements ApplicationRunner {

    @Autowired
    private Car car;

    @Autowired
    private GearTrackingAspect gearTrackingAspect;

    public static void main(String[] args) {
        SpringApplication.run(new Object[] {SpringAOPApplication.class, SpringAOPConfiguration.class}, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        car.drive();
        car.reverse();

        car.changeGear(1);
        car.changeGear(2);
        car.changeGear(3);
        car.changeGear(4);
        car.changeGear(3);
        car.changeGear(4);
        car.changeGear(5);
        car.changeGear(4);
        car.changeGear(2);
        car.changeGear(1);

        System.out.println(gearTrackingAspect.gearStat(1));
        System.out.println(gearTrackingAspect.gearStat(2));
        System.out.println(gearTrackingAspect.gearStat(3));
        System.out.println(gearTrackingAspect.gearStat(4));
        System.out.println(gearTrackingAspect.gearStat(5));
        System.out.println(gearTrackingAspect.gearStat(6));

        // We are able to cast the Car proxy to a Parking interface to invoke the park() method.
        ((Parking)car).park();
    }
}
