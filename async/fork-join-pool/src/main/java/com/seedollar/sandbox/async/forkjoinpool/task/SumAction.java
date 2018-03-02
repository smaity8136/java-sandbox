package com.seedollar.sandbox.async.forkjoinpool.task;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class SumAction extends RecursiveAction {

    private static final int SEQUENTIAL_THRESHOLD = 5;
    private List<Long> data;

    public SumAction(List<Long> data) {
        this.data = data;
    }

    @Override
    protected void compute() {
        if (SEQUENTIAL_THRESHOLD > data.size()) {
            System.out.println("Total = " + computeNormally());
        } else {
            int mid = data.size() /2;

            // Create 2 recursive actions
            SumAction subTask1 = new SumAction(data.subList(0, mid));
            SumAction subTask2 = new SumAction(data.subList(mid, data.size()));

            subTask1.fork();
            subTask2.compute();
            subTask1.join();
            // Or you can call the below which does the same
//            invokeAll(subTask1, subTask2);
        }

    }

    private Long computeNormally() {
        return this.data.stream().reduce(0l, Long::sum);
    }
}
