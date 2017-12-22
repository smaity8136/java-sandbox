package com.seedollar.java.streams.collector;

import com.google.common.collect.Lists;
import com.seedollar.java.domain.Cart;
import com.seedollar.java.domain.Price;
import com.seedollar.java.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomCollectorTest {

    private List<Product> productList = Lists.newArrayList(
            new Product("Ham", "Pork ham slices", new Price(30d, 25d, 8d)),
            new Product("Apple", "Red shinny apple", new Price(4.75d, 2d, 4d)),
            new Product("Pear", "A Juicy pear", new Price(3.5d, 0d, 4d)),
            new Product("Bread", "Sliced loaf of bread", new Price(2.40d, 5d, 0d)),
            new Product("Juice", "1L orange juice", new Price(13.26, 10d, 8d))
    );

    @Test
    @DisplayName("This test will illustrate how we can apply our custom CartCollector implementation")
    public void testCustomCollector() {
        Cart cart = productList.stream().collect(new CartCollector());
        System.out.println("cart = " + cart);
        Assertions.assertEquals(9.041, cart.getDiscountedAmount(), 0.001);
        Assertions.assertEquals(3.7908, cart.getTaxedAmount(), 0.001);
        Assertions.assertEquals(39.446, cart.getSubTotalAmount(), 0.001);
        Assertions.assertEquals(43.2367, cart.getTotalAmount(), 0.001);

    }

}
