package com.seedollar.java.streams.collector;

import com.google.common.collect.Sets;
import com.seedollar.java.domain.Cart;
import com.seedollar.java.domain.Product;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Shows how we can implement out own Collector, which consumes a list of Products and populates a Cart with aggregated amounts.
 */
public class CartCollector implements Collector<Product, Cart, Cart> {

    private Cart tempCart = new Cart();

    public CartCollector() {
    }

    @Override
    public Supplier<Cart> supplier() {
        return () -> tempCart;
    }

    @Override
    public BiConsumer<Cart, Product> accumulator() {
        return (c, p) -> {
            c = supplier().get();
            c.setDiscountedAmount(c.getDiscountedAmount() + p.getTargetPrice().getActualPrice() * p.getTargetPrice().getDiscountRate() / 100);
            c.setTaxedAmount(c.getTaxedAmount() + p.getTargetPrice().getActualPrice() * p.getTargetPrice().getTaxRate() / 100);
            c.setSubTotalAmount(c.getSubTotalAmount() + c.getDiscountedAmount());
            c.setTotalAmount(c.getSubTotalAmount() + c.getTaxedAmount());
        };
    }

    @Override
    public BinaryOperator<Cart> combiner() {
        return (a, b) -> a; // No combining required
    }

    @Override
    public Function<Cart, Cart> finisher() {
        return (c) -> c; // No conversion required
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet(Characteristics.CONCURRENT);
    }
}
