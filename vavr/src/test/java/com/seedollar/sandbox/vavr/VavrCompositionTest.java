package com.seedollar.sandbox.vavr;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VavrCompositionTest {

    Function1<String, String> trim = String::trim;
    Function1<String, String> toUpper = String::toUpperCase;
    Function1<String, Integer> countChars = String::length;
    Function1<Integer,Boolean> isEven = num -> num % 2 == 0;

    @Test
    @DisplayName("Test to illustrate vavr composition")
    public void testComposition() {

        // The order of execution is: toUpper(), trim(), countChars(), and then isEven()
        Boolean sentenceIsEven = toUpper
                .andThen(trim)
                .andThen(countChars)
                .andThen(isEven)
                .apply("   this is a sentence");
        Assertions.assertTrue(sentenceIsEven);

        // The order of execution would be trim(), toUpper(), countChars() and finally isEven()
        Function1<String, Boolean> composedIsEvenFunction = isEven.compose(countChars).compose(toUpper).compose(trim);
        Assertions.assertFalse(composedIsEvenFunction.apply(" this is a sentence!  "));
    }


    @Test
    @DisplayName("Test to illustrate vavr lifting.")
    public void testLifting() {

        // The lifting function
        Function1<String, String> moreThanTwoWords = (word) -> {
            String[] words = word.split("\\W+");
            if (words.length < 3)
                throw new IllegalArgumentException("Must at least be 3 words!");
            return word;
        };

        Function1<String, Boolean> isEvenComposed = isEven.compose(countChars).compose(trim).compose(toUpper).compose(moreThanTwoWords);

        Function1<String, Option<Boolean>> liftedFunction = Function1.lift(isEvenComposed);
        Option<Boolean> negativeResult = liftedFunction.apply("this is");
        Assertions.assertEquals(Option.none(), negativeResult);

        Option<Boolean> positiveResult = liftedFunction.apply("this is a real sentence!");
        Assertions.assertFalse(positiveResult.isEmpty());
        Assertions.assertTrue(positiveResult.get());
    }

    @Test
    @DisplayName("Test to illustrate vavr partial application")
    public void testPartialApplication() {

        Function2<String, String, String> greetingTemplate = (s1,s2) -> String.format("%s %s", s1, s2);
        Function1<String, String> englishGreeting = greetingTemplate.apply("Hello");
        Function1<String, String> spanishGreeting = greetingTemplate.apply("Ola");
        System.out.println("englishGreeting = " + englishGreeting.apply("Todd"));
        System.out.println("spanishGreeting = " + spanishGreeting.apply("Jimmy"));
    }

    @Test
    @DisplayName("Test to illustrate vavr curring")
    public void testCurring() {
        /**
         * Currying refers to transform the evaluation of a function that receives a tuple as argument to a well-defined sequence of
         * functions that evaluates only one argument each.
         */
        Function3<Integer, Integer, Integer, Integer> summation = (n1, n2, n3) ->   n1 + n2 + n3;
        Function1<Integer, Function1<Integer,Integer>> add2 = summation.curried().apply(2);
        Function1<Integer, Integer> add10 = add2.curried().apply(10);
        Integer result = add10.apply(8);
        Assertions.assertEquals(result.intValue(), 20);
    }

    @Test
    @DisplayName("Test to illustrate vavr memoization")
    public void testMemoization() {
        /**
         * The first calculation should take 3 seconds, but subsequent invocations of 'calculation() with same parameter value will be quicker because they
         * retrieve the value from the cache.
         */
        Function1<Integer, Integer> expensiveCalculation = n -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return n;
        };

        Function1<Integer, Integer> calculation = expensiveCalculation.memoized();

        StopWatch calculation1Timer = new StopWatch();
        calculation1Timer.start();
        calculation.apply(70);
        calculation1Timer.stop();

        StopWatch calculation2Timer = new StopWatch();
        calculation2Timer.start();
        calculation.apply(70);
        calculation2Timer.stop();

        StopWatch calculation3Timer = new StopWatch();
        calculation3Timer.start();
        calculation.apply(70);
        calculation3Timer.stop();

        System.out.println("calculation1Timer = " + calculation1Timer.getTotalTimeMillis());
        System.out.println("calculation2Timer = " + calculation2Timer.getTotalTimeMillis());
        System.out.println("calculation3Timer = " + calculation3Timer.getTotalTimeMillis());
    }
}
