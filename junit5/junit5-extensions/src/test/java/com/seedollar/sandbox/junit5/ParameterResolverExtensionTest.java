package com.seedollar.sandbox.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.seedollar.sandbox.junit5.annotation.Quattro;
import com.seedollar.sandbox.junit5.domain.*;
import com.seedollar.sandbox.junit5.extension.AudiParameterResolverExtension;
import com.seedollar.sandbox.junit5.extension.BMWParameterResolverExtension;
import com.seedollar.sandbox.junit5.extension.QuattroAnnotationParameterResolverExtension;

/**
 * Created by seedollar on 7/11/17.
 */
@ExtendWith(value = {AudiParameterResolverExtension.class, BMWParameterResolverExtension.class, QuattroAnnotationParameterResolverExtension.class})
public class ParameterResolverExtensionTest {

    @Test
    @DisplayName("Test the BMWParameterResolverExtension class which will return a new instance of X5 car.")
    public void testBMWParameter(BMW car) {
        Assertions.assertNotNull(car);
        Assertions.assertTrue(car instanceof X5);
        Assertions.assertTrue(car.isRunOnFlats());
    }

    @Test
    @DisplayName("Test the AudiParameterResolverExtension class which will return a new instance of RS5 car.")
    public void testAudiParameter(Audi car) {
        Assertions.assertNotNull(car);
        Assertions.assertTrue(car instanceof RS5);
        Assertions.assertTrue(car.isQuattro());
    }

    @Test
    @DisplayName("Test the Quattro class which will return a new instance of RS5 car.")
    public void testQuattroAnnotationParameter(@Quattro Car car) {
        Assertions.assertNotNull(car);
        Assertions.assertEquals("Q5", car.getModel());
        Assertions.assertTrue(((Audi)car).isQuattro());
    }
}
