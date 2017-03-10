package com.seedollar.spring.graphite;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import javaslang.control.Try;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by seedollar on 3/10/17.
 */
@SpringBootApplication
public class SpringGraphiteApplication {

    private static Meter consoleMeter;

    public SpringGraphiteApplication(Meter consoleMeter) {
        this.consoleMeter = consoleMeter;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphiteApplication.class, args);
        consoleMeter.mark();
        Try.of(() -> {
            Thread.sleep(3000);
            return 0;
        }).get();
    }
}
