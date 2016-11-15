package com.seedollar.java.sandbox.immutable;

import org.immutables.value.Value;

/**
 * Created by seedollar on 10/24/16.
 */
public interface Vehicle {

    /**
     * Create a custom builder implementation
     */
    interface VehicleBuilder {
        Vehicle build();
    }

    @Value.Immutable
    public abstract class Scooter implements Vehicle {
        public abstract static class Builder implements VehicleBuilder {}
    }

    @Value.Immutable
    public abstract class Truck implements Vehicle {
        public abstract static class Builder implements VehicleBuilder {}
    }

    class Builders {
        void buildIt(VehicleBuilder builder) {
            Vehicle vehicle = builder.build();
        }
    }
}
