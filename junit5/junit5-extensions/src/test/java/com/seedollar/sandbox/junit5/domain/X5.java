package za.co.seedollar.sandbox.junit5.domain;

/**
 * Created by seedollar on 7/11/17.
 */
public class X5 implements BMW, Car {

    public String getModel() {
        return "X5";
    }


    @Override
    public boolean isRunOnFlats() {
        return true;
    }
}
