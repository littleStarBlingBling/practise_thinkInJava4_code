package com.practise.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedDiningPhilosopher {
    public static void main(String[] args) throws Exception {
        int ponder = 0;

        ExecutorService exec = Executors.newCachedThreadPool();

        int size = 5;
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick(i);
        }

        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {
                exec.execute(new Philosopher(sticks[i], sticks[i + 1], i, ponder));
            } else {
                exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
            }

        }

        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();

    }
}
