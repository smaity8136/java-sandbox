package com.seedollar.java.streams;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.seedollar.java.domain.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectorsApiTest {

    // In this scenario we have a stream of prices, and we calculate the average discounted price over the collection.
    final List<Price> prices = Lists.newArrayList(
            new Price(54.22d, 1d, 6d, Price.PriceType.SALE),
            new Price(24.22d, 2d, 4d, Price.PriceType.SALE),
            new Price(105d, 1.5d, 2d, Price.PriceType.COST),
            new Price(14.50d, 1.5d, 5d, Price.PriceType.SALE));

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
                new Price(54.22d, 1d, 6d, Price.PriceType.SALE),
                new Price(24.22d, 2d, 6d, Price.PriceType.SALE),
                new Price(105d, 1.5d, 6d, Price.PriceType.COST),
                new Price(14.50d, 1.5d, 6d, Price.PriceType.SALE));

        Optional<Price> highestPriceOptional = prices.stream().reduce(highestOf);

        Assertions.assertTrue(highestPriceOptional.isPresent());
        Assertions.assertEquals(105d, highestPriceOptional.get().getActualPrice(), 0.001);
    }

    @Test
    @DisplayName("Test which illustrates the use of aggregating collector methods like summingInt()")
    public void testSummingInt() {
        Double totalActualPrice = prices.stream().collect(Collectors.summingDouble(Price::getActualPrice));
        Assertions.assertEquals(197.94, totalActualPrice, 0.01);
    }

    @Test
    @DisplayName("Show how to apply the 'collectingAndThen' function")
    public void testCollectingAndThen() {
        String totalDiscountRate = prices.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingDouble(Price::getDiscountRate), new DecimalFormat("0.000")::format));
        Assertions.assertEquals("1.500", totalDiscountRate);
    }

    @Test
    @DisplayName("Show how to use 'summarizingDouble()' to get a SummaryStatistics object which has a bunch of aggregate calculations")
    public void testSummarizingDouble() {
        DoubleSummaryStatistics summaryStatistics = prices.stream().collect(Collectors.summarizingDouble(Price::getActualPrice));
        Assertions.assertEquals(4, summaryStatistics.getCount());
        Assertions.assertEquals(49.485d, summaryStatistics.getAverage(), 0.001);
        Assertions.assertEquals(105d, summaryStatistics.getMax(), 0.001);
        Assertions.assertEquals(14.50d, summaryStatistics.getMin(), 0.001);
    }

    @Test
    @DisplayName("Show how to use 'mapping()' to bind results to a target collection")
    public void testMapping2() {
        Set<Long> priceIds = prices.stream().collect(Collectors.mapping(Price::getId, Collectors.toSet()));
        Assertions.assertEquals(4, priceIds.size());
    }

    @Test
    @DisplayName("Show how to apply the 'joining()' method on collected results")
    public void testJoining() {
        String taxRatesString = prices.stream().map(price -> String.valueOf(price.getTaxRate())).collect(Collectors.joining("|"));
        Assertions.assertEquals("6.0|4.0|2.0|5.0", taxRatesString);

        // Apply a joining prefix and postfix to the result
        String discountRatesString = prices.stream().map(price -> String.valueOf(price.getDiscountRate()))
                .collect(Collectors.joining("|", "Discount Rates = [", "]"));
        Assertions.assertEquals("Discount Rates = [1.0|2.0|1.5|1.5]", discountRatesString);
    }

    @Test
    @DisplayName("Show how to use the 'groupBy()' function")
    public void testGroupBy() {
        Map<Price.PriceType, List<Price>> results = prices.stream().collect(Collectors.groupingBy(Price::getPriceType));
        Assertions.assertEquals(1, results.get(Price.PriceType.COST).size());
        Assertions.assertEquals(3, results.get(Price.PriceType.SALE).size());
    }

    @Test
    @DisplayName("Show how to use the 'groupBy()' function with 'counting()' function downstream")
    public void testGroupByWithCounting() {
        Map<Price.PriceType, Long> results = prices.stream().collect(Collectors.groupingBy(Price::getPriceType, Collectors.counting()));
        Assertions.assertEquals(new Long(1), results.get(Price.PriceType.COST));
        Assertions.assertEquals(new Long(3), results.get(Price.PriceType.SALE));
    }

    @Test
    @DisplayName("Show how to use the 'groupBy()' function with 'averagingDouble()' function on a ")
    public void testGroupByWithAveraging() {
        Map<Price.PriceType, Double> results = prices.stream().collect(Collectors.groupingBy(Price::getPriceType, TreeMap::new, Collectors.averagingDouble(Price::getActualPrice)));
        Assertions.assertEquals(105d, results.get(Price.PriceType.COST), 0.01);
        Assertions.assertEquals(30.98d, results.get(Price.PriceType.SALE), 0.01);
    }

    @Test
    @DisplayName("Show how to apply the 'partitionBy()' function to a result Map<Boolean, List<T>>")
    public void testPartitionBy() {
        Map<Boolean, List<Price>> results = prices.stream().collect(Collectors.partitioningBy(price -> price.getActualPrice() > 60d));
        Assertions.assertEquals(1, results.get(Boolean.TRUE).size());
        Assertions.assertEquals(3, results.get(Boolean.FALSE).size());
    }

    @Test
    @DisplayName("Test groupBy")
    public void testGroupByAgain() {
        List<List<Object>> results = Lists.newArrayList(
                Lists.newArrayList("4011", "1111"),
                Lists.newArrayList("4011", "2222"),
                Lists.newArrayList("4011", "3333"),
                Lists.newArrayList(null, "6666"),
                Lists.newArrayList("4013", null),
                Lists.newArrayList("4012", "4444"),
                Lists.newArrayList("4012", "5555"),
                Lists.newArrayList("", "7777"));

        Set<Object> collect2 = results.stream().flatMap(List::stream).filter(Objects::nonNull).map(Object::toString).collect(Collectors.toSet());
        Assertions.assertNotNull(collect2);

//        results.stream().collect(Collectors.mapping(result -> result.))

//        Map<Object, Stream> collect1 = results.stream().collect(Collectors.toMap(result -> result.get(0), result -> ((List) result.get(1)).stream()));
//        Assertions.assertNotNull(collect1);

//        Object collect1 = results.stream().flatMap(result -> ((List) result.get(1)).stream()).collect(Collectors.toList());
//        Assertions.assertNotNull(collect1);

        Map<Object, List<Object>> collect = results.stream().collect(Collectors.groupingBy(result -> result.get(0),
                Collectors.mapping(res -> res.get(1), Collectors.toList())));
//        collect.entrySet().stream().flatMap(res -> )

        Assertions.assertNotNull(collect);
        List<String> collect1 = collect.entrySet().stream().map(entry -> entry.getKey() + "," + entry.getValue().stream().map(Object::toString).collect(Collectors.joining(","))).collect(Collectors.toList());
        Assertions.assertNotNull(collect1);

    }
}
