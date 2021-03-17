package com.practise.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car{
    private boolean waxOn = false;

    // 涂蜡
    public synchronized  void waxed(){
        // 可以抛光了
        waxOn = true;
        notifyAll();
    }

    // 抛光
    public synchronized void buffed(){
        // 可以涂另一层蜡了
        waxOn = false;
        notifyAll();
    }

    public synchronized  void waitForWaxing()throws InterruptedException{
        while (waxOn == false){
            wait();
        }
    }

    public synchronized void waitForBuffing()throws InterruptedException{
        while (waxOn == true){
            wait();
        }
    }
}

class WaxOn implements Runnable{
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println("Wax On!");
                // 模拟需要涂蜡的时间
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable{
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                car.waitForWaxing();
                System.out.println("Wax Off!");
                // 模拟需要抛光的时间
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}
