package com.practise.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Blocker{
    synchronized void waitingCall(){
        try{
            while (!Thread.interrupted()){
                wait();
                System.out.println(Thread.currentThread());
            }
        }catch (InterruptedException e){

        }
    }

    synchronized void prod(){
        notify();
    }

    synchronized void prodAll(){
        notifyAll();
    }
}

class Task implements Runnable{
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable{
    static  Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

public class NotifyVsNotifyAll {
    public static void main(String[] args)throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++){
            exec.execute(new Task());
        }
        exec.execute(new Task2());

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            boolean prod = true;

            @Override
            public void run() {
               if(prod){
                   System.out.println("\nnofity() ");
                   Task2.blocker.prod();
                   prod = false;
               }else {
                   System.out.println("\nnotifyAll()");
                   Task.blocker.prodAll();
                   prod = true;
               }
            }
        }, 400, 400);

        TimeUnit.SECONDS.sleep(3);
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        exec.shutdownNow();
        System.out.println("\nShutting down");
    }
}
