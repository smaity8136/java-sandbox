package com.seedollar.java.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by seedollar on 2016/02/03.
 */
@Service
public class RandomValueService {

    @Value("${random-string-manual}")
    private String randomStringManual;

    @Value("${random-string-auto}")
    private String randomStringAuto;

    @Value("${random-int-manual}")
    private Integer randomIntManual;

    @Value("${random-int-auto}")
    private Integer randomIntAuto;

    @Value("${random-long-manual}")
    private Long randomLongManual;

    @Value("${random-long-auto}")
    private Long randomLongAuto;

    @Value("${randomIntBetween4And11}")
    private Integer randomIntBetween4And11;

    public void printRandomValues() {

        StringBuilder output = new StringBuilder("\n============= RANDOM VALUES ================");
        output.append("\nRandom String Manual: ").append(randomStringManual);
        output.append("\nRandom String Auto: ").append(randomStringAuto);
        output.append("\nRandom Integer Manual: ").append(randomIntManual);
        output.append("\nRandom Integer Auto: ").append(randomIntAuto);
        output.append("\nRandom Long Manual: ").append(randomLongManual);
        output.append("\nRandom Long Auto: ").append(randomLongAuto);
        output.append("\n==========================================");
        assert(randomIntBetween4And11 >=4 && randomIntBetween4And11 <= 11);
        output.append("\nRandom Integer Between 4 and 11: ").append(randomIntBetween4And11);
        output.append("\n==========================================");
        System.out.println(output.toString());
    }
}
