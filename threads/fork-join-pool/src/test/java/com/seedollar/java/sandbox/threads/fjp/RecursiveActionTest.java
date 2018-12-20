package com.seedollar.java.sandbox.threads.fjp;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionTest {

    class CustomRecursiveAction extends RecursiveAction {

        private final int THRESHOLD = 4;
        private String work;

        public CustomRecursiveAction(String work) {
            this.work = work;
        }

        @Override
        protected void compute() {
            if (work.length() > THRESHOLD) {
                ForkJoinTask.invokeAll(createSubTasks());
            }

        }

        private List<CustomRecursiveAction> createSubTasks() {

        }
    }
}
