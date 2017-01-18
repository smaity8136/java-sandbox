package com.seedollar.java.sandbox.javaslang.unapply;

import com.seedollar.java.sandbox.javaslang.unapply.model.Dog;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.match.annotation.Patterns;
import javaslang.match.annotation.Unapply;

/**
 * Created by seedollar on 1/18/17.
 */
@Patterns
public class Custom {

    @Unapply
    static Tuple2<String, String> Dog(Dog dog) {
        return Tuple.of(dog.getName(), dog.getBreed());
    }
}
