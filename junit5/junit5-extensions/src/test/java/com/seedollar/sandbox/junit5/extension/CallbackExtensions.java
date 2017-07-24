package com.seedollar.sandbox.junit5.extension;

import org.junit.jupiter.api.extension.*;

/**
 * Created by seedollar on 7/11/17.
 */
public class CallbackExtensions implements BeforeAllCallback, BeforeTestExecutionCallback, BeforeEachCallback, AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback {

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        System.out.println("invoking afterTestExecution()");
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        System.out.println("invoking beforeTestExecution()");
    }

    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        System.out.println("invoking beforeAll()");
    }

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        System.out.println("invoking afterAll()");
    }

    @Override
    public void afterEach(TestExtensionContext context) throws Exception {
        System.out.println("invoking afterEach() - " + context.getDisplayName());
    }

    @Override
    public void beforeEach(TestExtensionContext context) throws Exception {
        System.out.println("invoking beforeEach() - " + context.getDisplayName());
    }
}
