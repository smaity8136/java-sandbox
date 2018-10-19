package com.seedollar.java.streams.collector;

import com.google.common.collect.Lists;
import com.seedollar.java.domain.Cart;
import com.seedollar.java.domain.TargetPrice;
import com.seedollar.java.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomCollectorTest {

    private List<Product> productList = Lists.newArrayList(
            new Product("Ham", "Pork ham slices", new TargetPrice(30d, 25d, 8d, TargetPrice.PriceType.SALE)),
            new Product("Apple", "Red shinny apple", new TargetPrice(4.75d, 2d, 4d, TargetPrice.PriceType.COST)),
            new Product("Pear", "A Juicy pear", new TargetPrice(3.5d, 0d, 4d, TargetPrice.PriceType.SALE)),
            new Product("Bread", "Sliced loaf of bread", new TargetPrice(2.40d, 5d, 0d, TargetPrice.PriceType.COST)),
            new Product("Juice", "1L orange juice", new TargetPrice(13.26, 10d, 8d, TargetPrice.PriceType.COST))
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
