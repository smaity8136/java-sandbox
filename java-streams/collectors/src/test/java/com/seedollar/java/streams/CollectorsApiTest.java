package com.seedollar.java.streams;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.seedollar.java.domain.TargetPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsApiTest {

    // In this scenario we have a stream of targetPrices, and we calculate the average discounted price over the collection.
    final List<TargetPrice> targetPrices = Lists.newArrayList(
            new TargetPrice(54.22d, 1d, 6d, TargetPrice.PriceType.SALE),
            new TargetPrice(24.22d, 2d, 4d, TargetPrice.PriceType.SALE),
            new TargetPrice(105d, 1.5d, 2d, TargetPrice.PriceType.COST),
            new TargetPrice(14.50d, 1.5d, 5d, TargetPrice.PriceType.SALE));

    @Test
    @DisplayName("Show how to apply the Collectors.averagingDouble() function using the Collectors class")
    public void testAveragingDouble() {
        Double averageDiscountedPrices = targetPrices.stream().collect(Collectors.averagingDouble((p -> p.getActualPrice() * (p.getDiscountRate() / 100))));
        System.out.println("discountedPrices = " + averageDiscountedPrices);
    }

    @Test
    @DisplayName("Show how to apply the Collectors.toMap() function using the Collectors class")
    public void testToMap() {
        // We generate a Map using the price's ID as the key and taxRate as the value
        Map<Long, Double> taxRateMap = targetPrices.stream().collect(Collectors.toMap(TargetPrice::getId, TargetPrice::getTaxRate));
        Assertions.assertEquals(4, taxRateMap.size());
    }

    @Test
    @DisplayName("Show how to apply the Collectors.partitioningBy() function using the Collectors class")
    public void testPartitioningBy() {
        // We partition the targetPrices by those who's tax rate is greater than 3, and those that are less than 3, generating a map
        Map<Boolean, List<TargetPrice>> taxRatePartitionedPrices = targetPrices.stream().collect(Collectors.partitioningBy(p -> p.getTaxRate() > 3));
        Assertions.assertEquals(3, taxRatePartitionedPrices.get(true).size());
        Assertions.assertEquals(1, taxRatePartitionedPrices.get(false).size());
    }

    @Test
    @DisplayName("Show how to apply the Collectors.mapping() function using the Collectors class")
    public void testMapping() {
        // We calculate the discounted amount for each price through the mapping() function and collect the amounts as a Set.
        Set<Double> discountedAmounts = targetPrices.stream().collect(
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
        Comparator<TargetPrice> actualPriceComparator = Comparator.comparingDouble(TargetPrice::getActualPrice);
        BinaryOperator<TargetPrice> highestOf = BinaryOperator.maxBy(actualPriceComparator);

        List<TargetPrice> targetPrices = Lists.newArrayList(
                new TargetPrice(54.22d, 1d, 6d, TargetPrice.PriceType.SALE),
                new TargetPrice(24.22d, 2d, 6d, TargetPrice.PriceType.SALE),
                new TargetPrice(105d, 1.5d, 6d, TargetPrice.PriceType.COST),
                new TargetPrice(14.50d, 1.5d, 6d, TargetPrice.PriceType.SALE));

        Optional<TargetPrice> highestPriceOptional = targetPrices.stream().reduce(highestOf);

        Assertions.assertTrue(highestPriceOptional.isPresent());
        Assertions.assertEquals(105d, highestPriceOptional.get().getActualPrice(), 0.001);
    }

    @Test
    @DisplayName("Test which illustrates the use of aggregating collector methods like summingInt()")
    public void testSummingInt() {
        Double totalActualPrice = targetPrices.stream().collect(Collectors.summingDouble(TargetPrice::getActualPrice));
        Assertions.assertEquals(197.94, totalActualPrice, 0.01);
    }

    @Test
    @DisplayName("Show how to apply the 'collectingAndThen' function")
    public void testCollectingAndThen() {
        String totalDiscountRate = targetPrices.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingDouble(TargetPrice::getDiscountRate), new DecimalFormat("0.000")::format));
        Assertions.assertEquals("1.500", totalDiscountRate);
    }

    @Test
    @DisplayName("Show how to use 'summarizingDouble()' to get a SummaryStatistics object which has a bunch of aggregate calculations")
    public void testSummarizingDouble() {
        DoubleSummaryStatistics summaryStatistics = targetPrices.stream().collect(Collectors.summarizingDouble(TargetPrice::getActualPrice));
        Assertions.assertEquals(4, summaryStatistics.getCount());
        Assertions.assertEquals(49.485d, summaryStatistics.getAverage(), 0.001);
        Assertions.assertEquals(105d, summaryStatistics.getMax(), 0.001);
        Assertions.assertEquals(14.50d, summaryStatistics.getMin(), 0.001);
    }

    @Test
    @DisplayName("Show how to use 'mapping()' to bind results to a target collection")
    public void testMapping2() {
        Set<Long> priceIds = targetPrices.stream().collect(Collectors.mapping(TargetPrice::getId, Collectors.toSet()));
        Assertions.assertEquals(4, priceIds.size());
    }

    @Test
    @DisplayName("Show how to apply the 'joining()' method on collected results")
    public void testJoining() {
        String taxRatesString = targetPrices.stream().map(targetPrice -> String.valueOf(targetPrice.getTaxRate())).collect(Collectors.joining("|"));
        Assertions.assertEquals("6.0|4.0|2.0|5.0", taxRatesString);

        // Apply a joining prefix and postfix to the result
        String discountRatesString = targetPrices.stream().map(targetPrice -> String.valueOf(targetPrice.getDiscountRate()))
                .collect(Collectors.joining("|", "Discount Rates = [", "]"));
        Assertions.assertEquals("Discount Rates = [1.0|2.0|1.5|1.5]", discountRatesString);
    }

    @Test
    @DisplayName("Show how to use the 'groupBy()' function")
    public void testGroupBy() {
        Map<TargetPrice.PriceType, List<TargetPrice>> results = targetPrices.stream().collect(Collectors.groupingBy(TargetPrice::getPriceType));
        Assertions.assertEquals(1, results.get(TargetPrice.PriceType.COST).size());
        Assertions.assertEquals(3, results.get(TargetPrice.PriceType.SALE).size());
    }

    @Test
    @DisplayName("Show how to use the 'groupBy()' function with 'counting()' function downstream")
    public void testGroupByWithCounting() {
        Map<TargetPrice.PriceType, Long> results = targetPrices.stream().collect(Collectors.groupingBy(TargetPrice::getPriceType, Collectors.counting()));
        Assertions.assertEquals(new Long(1), results.get(TargetPrice.PriceType.COST));
        Assertions.assertEquals(new Long(3), results.get(TargetPrice.PriceType.SALE));
    }

    @Test
    @DisplayName("Show how to use the 'groupBy()' function with 'averagingDouble()' function on a ")
    public void testGroupByWithAveraging() {
        Map<TargetPrice.PriceType, Double> results = targetPrices.stream().collect(Collectors.groupingBy(TargetPrice::getPriceType, TreeMap::new, Collectors.averagingDouble(TargetPrice::getActualPrice)));
        Assertions.assertEquals(105d, results.get(TargetPrice.PriceType.COST), 0.01);
        Assertions.assertEquals(30.98d, results.get(TargetPrice.PriceType.SALE), 0.01);
    }

    @Test
    @DisplayName("Show how to apply the 'partitionBy()' function to a result Map<Boolean, List<T>>")
    public void testPartitionBy() {
        Map<Boolean, List<TargetPrice>> results = targetPrices.stream().collect(Collectors.partitioningBy(targetPrice -> targetPrice.getActualPrice() > 60d));
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

	@Test
    @DisplayName("Illustrate groupingBy() and filtering()")
    public void testGroupingByAndFiltering() {
        Map<TargetPrice.PriceType, List<TargetPrice>> results = targetPrices.stream().collect(Collectors.groupingBy(TargetPrice::getPriceType, Collectors.filtering(targetPrice -> targetPrice.getActualPrice() > 30d, Collectors.toList())));
        Assertions.assertTrue(results.get(TargetPrice.PriceType.SALE).size() == 1);
    }
}
