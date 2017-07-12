package za.co.seedollar.sandbox.junit5;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import za.co.seedollar.sandbox.junit5.extension.CustomTestExceptionExtension;

/**
 * Created by seedollar on 7/12/17.
 */
@ExtendWith(CustomTestExceptionExtension.class)
public class TestExecutionExceptionHandlerExtensionTest {

    @Ignore // We ignore this test as the actual test is to see a CustomTestException being thrown instead of a NullPointerException
    @Test
    @DisplayName("This test will confirm that the CustomTestExceptionExtension will catch NullPointerExceptions and re-throw them as CustomTestExceptions")
    public void testCatchNullPointerExceptions() {
        throw new NullPointerException("test");
    }

    @Test
    @DisplayName("This test will confirm that IllegalArgumentExeptions will be ignored when thrown")
    public void testIgnoreIllegalArgumentException() {
        throw new IllegalArgumentException("test");
    }
}
