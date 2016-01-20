package com.seedollar.lambda.builder;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by seedollar on 2016/01/19.
 */
public class LambdaPojoBuilderTest {

    @Test
    public void testLambdaPojoBuilder() {
        LambdaPojoBuilder<Car> carLambdaPojoBuilder = new CarPojoBuilder();

        Car bmwM3 = carLambdaPojoBuilder.build(c -> c.setMake("BMW"), c -> c.setModel("M3"), c -> c.setYear(2015));
        Assert.assertNotNull(bmwM3);
        Assert.assertEquals("BMW", bmwM3.getMake());
        Assert.assertEquals("M3", bmwM3.getModel());
        Assert.assertEquals(2015, bmwM3.getYear());

        // Now test making amendments
        carLambdaPojoBuilder.enrich(bmwM3, c -> c.setModel("320i"));
        Assert.assertEquals("BMW", bmwM3.getMake());
        Assert.assertEquals("320i", bmwM3.getModel());
        Assert.assertEquals(2015, bmwM3.getYear());
    }

    /**
     * Mock POJO
     */
    class Car {

        private String make;
        private String model;
        private int year;

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    /**
     * PojoBuilder implementation for Car
     */
    class CarPojoBuilder implements LambdaPojoBuilder<Car> {
        @Override
        public Car newInstance() {
            return new Car();
        }
    }
}
