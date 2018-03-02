package com.seedollar.sandbox.async.forkjoinpool.task;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {

    private static final int SEQUENTIAL_THRESHOLD = 1000000;
    List<Long> data;

    public SumTask(List<Long> data) {
        this.data = data;
    }

    @Override
    protected Long compute() {

        if (SEQUENTIAL_THRESHOLD > data.size()) {
            return computeNormally();
        } else {
            int mid = data.size() / 2;
            SumTask sumTask1 = new SumTask(data.subList(0, mid));
            SumTask sumTask2 = new SumTask(data.subList(mid, data.size()));
            // queue first task
//            sumTask1.fork();
            // compute and join the 2 tasks
//            return sumTask2.compute() + sumTask1.join();

            Collection<SumTask> allTasks = invokeAll(Sets.newHashSet(sumTask1, sumTask2));
            return allTasks.stream().map(task -> task.join()).reduce(0l, Long::sum);
        }
    }

    private long computeNormally() {
        return data.stream().reduce(0l, Long::sum);
    }
}
