package com.seedollar.sandbox.junit5.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import com.seedollar.sandbox.junit5.TestInstancePostProcessorExtensionTest;

/**
 * Created by seedollar on 7/11/17.
 */
public class TestPostProcessorExtension implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        System.out.println("testInstance = " + testInstance.getClass().getSimpleName());
        if (testInstance instanceof TestInstancePostProcessorExtensionTest) {
            // Execute the custom method
            ((TestInstancePostProcessorExtensionTest)testInstance).customMethod();
        }
    }
}
