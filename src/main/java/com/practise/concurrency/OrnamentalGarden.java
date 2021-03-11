package com.practise.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count{
    private int count = 0;
    private Random random = new Random(47);

    // 移除 synchronized 程序会失败
    public synchronized int increment(){
        int temp = count;
        // 一半的时间在让步
        if(random.nextBoolean()){
            Thread.yield();
        }

        return (count = ++temp);

    }

    public synchronized int value(){
        return count;
    }
}

class Entrance implements Runnable{
    // 跟踪花园参观者的主计数值
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<>();

    private int number = 0;
    private final int id;

    private static volatile boolean canceled = false;
    public static void cancel(){
        canceled = true;
    }

    public Entrance(int id){
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run() {
        while (!canceled){
            synchronized (this){
                ++number;
            }

            System.out.println(this + " Total：" + count.increment());
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch (InterruptedException e){
                System.out.println("sleep interrupted");
            }
        }

        System.out.println("Stopping " + this);
    }

    public synchronized int getValue(){
        return number;
    }

    @Override
    public String toString() {
        return "Entrance " + id + "：" + getValue();
    }

    public static int getTotalCount(){
        return count.value();
    }

    public static int sumEntrances(){
        int sum = 0;
        for(Entrance entrance : entrances){
            sum += entrance.getValue();
        }

        return sum;
    }
}

public class OrnamentalGarden {

    public static void main(String[] args)  throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++){
            executorService.execute(new Entrance(i));
        }

        TimeUnit.SECONDS.sleep(1);
        Entrance.cancel();
        executorService.shutdown();
        if(!executorService.awaitTermination(250, TimeUnit.MICROSECONDS)){
            System.out.println("Some tasks were not terminated!");
        }

        System.out.println("Total：" + Entrance.getTotalCount());
        System.out.println("Sum of Entrances：" + Entrance.sumEntrances());
    }
}
