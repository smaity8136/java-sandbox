package za.co.seedollar.sandbox.junit5.domain;

/**
 * Created by seedollar on 7/11/17.
 */
public class A4 implements Audi, Car {
    @Override
    public String getModel() {
        return "A4";
    }

    @Override
    public boolean isQuattro() {
        return false;
    }
}
