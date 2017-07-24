package com.seedollar.sandbox.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.seedollar.sandbox.junit5.extension.TestPostProcessorExtension;

/**
 * Created by seedollar on 7/11/17.
 */
@ExtendWith(TestPostProcessorExtension.class)
public class TestInstancePostProcessorExtensionTest {

    @Test
    public void testTestInstancePostProcessor() {
        System.out.println("Executing test...");
    }

    public void customMethod() {
        System.out.println("Executing TestInstancePostProcessorExtensionTest.customMethod()");
    }
}
