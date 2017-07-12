package za.co.seedollar.sandbox.junit5.extension;

import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestExtensionContext;
import za.co.seedollar.sandbox.junit5.exception.CustomTestException;

/**
 * Created by seedollar on 7/12/17.
 */
public class CustomTestExceptionExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(TestExtensionContext context, Throwable throwable) throws Throwable {
        // Catch  a NullPointerException
        if (throwable instanceof NullPointerException) {
            throw new CustomTestException("A null pointer exception was caught.");
        }

        // Ignore any IllegalArgumentExceptions
        if (throwable instanceof IllegalArgumentException) {
            return;
        }

        // Process any other exception as normal
        throw throwable;
    }
}
