package com.practise.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadlockingDiningPhilosophers {
    public static void main(String[] args)throws Exception {
        // 每个哲学家花费在思考上的时间，值越小，死锁发生的越快
        int ponder = 0;

        ExecutorService exec = Executors.newCachedThreadPool();

        // 一共五根筷子，通过数组的索引来标识
        int size = 5;
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }

        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i , ponder));
        }

        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
