package com.practise.concurrency;

import java.util.concurrent.TimeUnit;

class NeedsCleanup{
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup(){
        System.out.println("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable{
    private volatile double d = 0.0;

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                // point1
                NeedsCleanup n1 = new NeedsCleanup(1);
                try{
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);

                    // point2
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try{
                        System.out.println("Calculating");
                        for(int i = 1; i< 2500000; i++){
                            d = d + (Math.PI + Math.E) / d;
                        }

                        System.out.println("Finished time-consuming operation");
                    }finally {
                        n2.cleanup();
                    }
                }finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while() test");
        }catch (InterruptedException e){
            System.out.println("Exiting via InterruptedException");
        }
    }
}

public class InterruptingIdiom {
    public static void main(String[] args) throws Exception{
       Thread thread = new Thread(new Blocked3());
       thread.start();
       TimeUnit.MILLISECONDS.sleep(990);
       thread.interrupt();
    }
}
