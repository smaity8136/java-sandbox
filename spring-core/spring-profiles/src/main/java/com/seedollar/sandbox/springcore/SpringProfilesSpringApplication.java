package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.configuration.*;
import com.seedollar.sandbox.springcore.domain.Biller;
import com.seedollar.sandbox.springcore.domain.Container;
import com.seedollar.sandbox.springcore.domain.OpsBiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {DevProfileConfiguration.class, UatProfileConfiguration.class, PerfProfileConfiguration.class, ProdProfileConfiguration.class})
public class SpringProfilesSpringApplication implements ApplicationRunner {

    @Autowired
    Biller biller;

    @Autowired
    Container container;

    public static void main(String[] args) {
        SpringApplication.run(new Object[] {SpringProfilesConfiguration.class, SpringProfilesSpringApplication.class}, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("biller: $" + biller.getClass().getSimpleName());
        System.out.println("biller amount: $" + biller.billableAmount());
        System.out.println("container: " + container.getName() + " Thread Count(" + container.getThreadCount() + ")");
    }
}
