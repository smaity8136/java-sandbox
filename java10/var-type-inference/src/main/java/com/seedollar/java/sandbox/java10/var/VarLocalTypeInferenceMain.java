package com.seedollar.java.sandbox.java10.var;

import java.util.List;

public class VarLocalTypeInferenceMain {

    public static void main(String[] args) {
        var cars = List.of("BMW", "Volvo", "Toyota");
        cars.stream().map(String::toUpperCase).forEach(System.out::println);

        var label = "test label";
        System.out.println("label.getClass() = " + label.getClass().getCanonicalName());

        // We can still use "var" as a variable name
        var var = 979.2;
        System.out.println("var = " + var);

    }
}
