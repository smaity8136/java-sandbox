package com.seedollar.java.sandbox.java9;

import com.seedollar.java.sandbox.java9.domain.Claim;

import java.util.Optional;

public interface PrivateMethodInterface {

    // A default method (Java 8 feature)
    default String process(Claim claim) {
        Claim processClaim = Optional.of(claim).orElseGet(() -> new Claim("default", "default description", false));
        return enrich(processClaim);
    }

    // Java 9 feature
    private static boolean isActive(Claim claim) {
        return claim.isActive();
    }

    // Java 9 feature
    // Private methods to help with decomposing default methods in interfaces
    private String enrich(Claim claim) {
        return isActive(claim) ? "authorized" : "unauthorized";
    }

}
