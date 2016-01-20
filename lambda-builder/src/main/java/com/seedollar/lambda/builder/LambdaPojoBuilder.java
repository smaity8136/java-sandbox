package com.seedollar.lambda.builder;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by seedollar on 2016/01/19.
 * Builder which allows concrete implementations to enrich their target instance using lambda expressions
 */
public interface LambdaPojoBuilder<T> {

    /**
     * Lambda expression to enrich a target instance.
     *
     * @param <T>
     */
    @FunctionalInterface
    interface Enricher<T> extends Consumer<T> {
    }

    /**
     * The default implementation to iterate the lambda expressions and execute them. A new instance of the target is created each time this method is invoked.
     *
     * @param enrichers
     * @return
     */
    default T build(Enricher<T>... enrichers) {
        T instance = newInstance();
        Stream.of(enrichers).forEach(t -> t.accept(instance));
        return instance;
    }

    /**
     * The default implementation to iterate the lambda expressions and execute them on an the provided target instance
     *
     * @param enrichers
     * @return
     */
    default T enrich(T currentInstance, Enricher<T>... enrichers) {
        Stream.of(enrichers).forEach(t -> t.accept(currentInstance));
        return currentInstance;
    }

    T newInstance();
}
