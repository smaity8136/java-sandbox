package com.seedollar.java.streams;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.seedollar.java.domain.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class CollectorsApiTest {

    // In this scenario we have a stream of prices, and we calculate the average discounted price over the collection.
    final List<Price> prices = Lists.newArrayList(
            new Price(54.22d, 1d, 6d),
            new Price(24.22d, 2d, 4d),
            new Price(105d, 1.5d, 2d),
            new Price(14.50d, 1.5d, 5d));

    @Test
    @DisplayName("Show how to apply the Collectors.averagingDouble() function using the Collectors class")
    public void testAveragingDouble() {
        Double averageDiscountedPrices = prices.stream().collect(Collectors.averagingDouble((p -> p.getActualPrice() * (p.getDiscountRate() / 100))));
        System.out.println("discountedPrices = " + averageDiscountedPrices);
    }

    @Test
    @DisplayName("Show how to apply the Collectors.toMap() function using the Collectors class")
    public void testToMap() {
        // We generate a Map using the price's ID as the key and taxRate as the value
        Map<Long, Double> taxRateMap = prices.stream().collect(Collectors.toMap(Price::getId, Price::getTaxRate));
        Assertions.assertEquals(4, taxRateMap.size());
    }

    @Test
    @DisplayName("Show how to apply the Collectors.partitioningBy() function using the Collectors class")
    public void testPartitioningBy() {
        // We partition the prices by those who's tax rate is greater than 3, and those that are less than 3, generating a map
        Map<Boolean, List<Price>> taxRatePartitionedPrices = prices.stream().collect(Collectors.partitioningBy(p -> p.getTaxRate() > 3));
        Assertions.assertEquals(3, taxRatePartitionedPrices.get(true).size());
        Assertions.assertEquals(1, taxRatePartitionedPrices.get(false).size());
    }

    @Test
    @DisplayName("Show how to apply the Collectors.mapping() function using the Collectors class")
    public void testMapping() {
        // We calculate the discounted amount for each price through the mapping() function and collect the amounts as a Set.
        Set<Double> discountedAmounts = prices.stream().collect(
                Collectors.mapping(p -> p.getActualPrice() * p.getDiscountRate() / 100, Collectors.toSet()));
        // We sum the discounted amounts
        Assertions.assertEquals(2.8190999999999997d, discountedAmounts.stream().reduce(0d, (a, b) -> a + b), 0.0001);
    }

    @Test
    @DisplayName("Test to show how to fully utilize the Collectors.collect() method to provide a container, populate it and combine it")
    public void testCollectorsCreatePopulateCombine() {
        HashSet<String> strings = Sets.newHashSet("a", "b", "c", "d", "e", "f", "g", "h", "i");
        StringBuilder stringBuilder = strings.stream().collect(StringBuilder::new, (sb, s) -> sb.append(s + "_"), StringBuilder::append);
        Assertions.assertEquals("a_b_c_d_e_f_g_h_i_", stringBuilder.toString());
    }

    @Test
    @DisplayName("Test which illustrates how we can apply a Binary Operator to a stream to perform an aggregate")
    public void testBinaryOperatorReduction() {
        Comparator<Price> actualPriceComparator = Comparator.comparingDouble(Price::getActualPrice);
        BinaryOperator<Price> highestOf = BinaryOperator.maxBy(actualPriceComparator);

        List<Price> prices = Lists.newArrayList(
                new Price(54.22d, 1d, 6d),
                new Price(24.22d, 2d, 6d),
                new Price(105d, 1.5d, 6d),
                new Price(14.50d, 1.5d, 6d));

        Optional<Price> highestPriceOptional = prices.stream().reduce(highestOf);

        Assertions.assertTrue(highestPriceOptional.isPresent());
        Assertions.assertEquals(105d, highestPriceOptional.get().getActualPrice(), 0.001);
    }
}
