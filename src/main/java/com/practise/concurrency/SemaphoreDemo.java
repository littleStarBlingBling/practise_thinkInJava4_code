package com.practise.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 将资源签入签出对象池的任务
class CheckoutTask<T> implements Runnable{
   private static int counter = 0;
   private final int id = counter++;
   private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try{
            T item = pool.checkOut();
            System.out.println(this + "checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + "checking in " + item);
            pool.checkIn(item);
        }catch (InterruptedException e){

        }
    }

    @Override
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}


public class SemaphoreDemo {
    final static int SIZE = 8;

    public static void main(String[] args) throws Exception{
        // 在创建对象池时，生成 8 个 Fat 对象
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);

        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < SIZE; i++){
            exec.execute(new CheckoutTask<>(pool));
        }
        System.out.println("All CheckoutTasks created");

        // 把所有对象签出
        List<Fat> list = new ArrayList<>();
        for(int i = 0; i < SIZE; i++){
          Fat fat = pool.checkOut();
            System.out.println(i + ": main() thread checked out ");
            fat.operation();
            list.add(fat);
        }

        // 没有对象了， blocked 线程会被阻塞
        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    pool.checkOut();
                }catch (InterruptedException e){
                    System.out.println("checkOut() Interrupted");
                }
            }
        });

        // 两秒之后取消 blocked 线程
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in " + list);
        for(Fat fat : list){
            pool.checkIn(fat);
        }

        exec.shutdown();
    }
}
