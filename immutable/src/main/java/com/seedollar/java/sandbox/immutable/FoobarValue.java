package com.seedollar.java.sandbox.immutable;

import org.immutables.value.Value;

import java.util.List;
import java.util.Set;

/**
 * Created by seedollar on 10/24/16.
 */
@Value.Immutable
public abstract class FoobarValue {

    public abstract int foo();
    public abstract String bar();
    public abstract List<Integer> buz();
    public abstract Set<Long> crux();
}
