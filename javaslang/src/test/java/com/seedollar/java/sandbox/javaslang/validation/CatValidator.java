package com.seedollar.java.sandbox.javaslang.validation;

import javaslang.collection.List;
import javaslang.control.Validation;

/**
 * Created by seedollar on 1/4/17.
 */
public class CatValidator {
    public Validation<List<String>, Cat> validateCat(String name, String breed) {
        return Validation.combine(validateName(name), validateBreed(breed)).ap(Cat::new);
    }

    private Validation<String, String> validateName(String name) {
        return (name.isEmpty()) ? Validation.invalid("Name is invalid") : Validation.valid(name);
    }

    private Validation<String, String> validateBreed(String breed) {
        return breed.equalsIgnoreCase("ginger") ? Validation.valid(breed) : Validation.invalid("Not a cat");
    }
}
