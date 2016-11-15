package com.seedollar.java.sandbox.immutable;

/**
 * Created by seedollar on 10/24/16.
 */
public class ImmutableExampleMain {

    public static void main(String[] args) {

        FoobarValue value = ImmutableFoobarValue.builder()
                .foo(12)
                .bar("Barr")
                .addBuz(1,4,5,6)
                .addCrux(973l, 33343l)
                .build();

        FoobarValue copyValue = ImmutableFoobarValue.builder().from(value).bar("Tedd").build();

        System.out.println("foo = " + value.foo());
        System.out.println("bar = " + value.bar());
        System.out.println("Buz = " + value.buz());
        System.out.println("Crux = " + value.crux());

        System.out.println("bar copy = " + copyValue.bar());

        SnafuInterface snafu = ImmutableSnafuInterface.builder().build();
        System.out.println("snafu = " + snafu);


    }
}
