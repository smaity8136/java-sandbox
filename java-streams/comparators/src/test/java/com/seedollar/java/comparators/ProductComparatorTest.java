package com.seedollar.java.comparators;

import com.google.common.collect.Lists;
import com.seedollar.java.domain.Price;
import com.seedollar.java.domain.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;

public class ProductComparatorTest {

    @Test
    public void testCollectionsComparator() {

        Function<Product, String> byName = p -> p.getName();
        Function<Product, Double> byPrice = p -> p.getPrice().getActualPrice();

        ArrayList<Product> products = Lists.newArrayList(
                new Product("Tomatoes", "Red tomatoes", new Price(1.35d, 0d, 13d)),
                new Product("Apples", "Royal Troon", new Price(2.5d, 0d, 13d)),
                new Product("Carrots", "Large carrots", new Price(1.89d, 0d, 13d))
        );

        Collections.sort(products, Comparator.comparing(byPrice).thenComparing(byName).reversed());
        System.out.println("products = " + products);
    }
}
