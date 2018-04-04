package com.seedollar.sandbox.ea.async;

import com.ea.async.Async;package com.ea.import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class EAAsyncExampleMain {

    public static void main(String[] args) {

        EAAsyncExampleMain eaMain = new EAAsyncExampleMain();
        eaMain.calculate();


        Double sumAmount = sumAmount();
        Double total = sumAmount;
        Double taxAmount = Async.await(sumAmount())
    }

    public  CompletableFuture<Double> sumAmount() {
        Double totalAmount = Async.await(new CompletableFuture<Double>()  {return new Random().doubles(1000, 1, 100).sum(); });
        return null;
    }

    public CompletableFuture<Double> calculate() {
        Double totalAmount = new Random().doubles(1000, 1, 100).sum();
        Double taxAmount = Async.await(calculateTaxAmount(totalAmount));

    }

    private Future<Double> calculateTaxAmount(Double totalAmount) {

        return null;
    }
}
