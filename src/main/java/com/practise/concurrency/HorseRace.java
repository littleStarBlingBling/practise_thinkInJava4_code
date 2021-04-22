package com.practise.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    // 随机数：0、1、2 代表马每次前进的步数
                    strides += random.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {

        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder stringBuilder = new StringBuilder();
        // 将步数转换为 * 符号
        for (int i = 0; i < getStrides(); i++) {
            stringBuilder.append("*");
        }
        stringBuilder.append(id);
        return stringBuilder.toString();
    }
}

public class HorseRace {
    // 终点线
    static final int FINISH_LINE = 10;
    private List<Horse> horseList = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int horseNum, int pause) {
        barrier = new CyclicBarrier(horseNum, new Runnable() {
            @Override
            public void run() {
                // 首先打印出赛道长度
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    stringBuilder.append("=");
                }
                System.out.println(stringBuilder);

                // 回合制比赛开始
                for (Horse horse : horseList) {
                    System.out.println(horse.tracks());
                }

                // 获胜条件判断
                for (Horse horse : horseList) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + "won!");
                        exec.shutdownNow();
                        return;
                    }
                }

                // 等待下回合的开始
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.println("barrier-action sleep interrupted");
                }
            }
        });

        for (int i = 0; i < horseNum; i++) {
            Horse horse = new Horse(barrier);
            horseList.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int horseNum = 5;
        int pause = 200;
        new HorseRace(horseNum, pause);
    }
}
