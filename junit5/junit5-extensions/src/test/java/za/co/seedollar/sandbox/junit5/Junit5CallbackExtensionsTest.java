package za.co.seedollar.sandbox.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import za.co.seedollar.sandbox.junit5.extension.CallbackExtensions;

/**
 * Created by seedollar on 7/11/17.
 */
@ExtendWith(CallbackExtensions.class)
public class Junit5CallbackExtensionsTest {

    @Test
    public void callbackExtensionsTest1() {
        System.out.println("Executing test 1...");
    }

    @Test
    public void callbackExtensionsTest2() {
        System.out.println("Executing test 2...");
    }
}
