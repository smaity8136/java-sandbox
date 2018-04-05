package com.seedollar.java.sandbox.java9;

import com.seedollar.java.sandbox.java9.domain.Claim;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrivateMethodInterfaceTest {

    @Test
    @DisplayName("Test the private methods of an interface")
    public void testPrivateMethodInterface() {
        Claim newClaim = new Claim("test1", "test1 description", true);
        PrivateMethodInterface privateMethodInterface = new PrivateMethodInterfaceImpl();
        Assertions.assertEquals("authorized", privateMethodInterface.process(newClaim));
    }
}
