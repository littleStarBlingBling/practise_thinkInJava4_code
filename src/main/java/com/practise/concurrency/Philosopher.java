package com.practise.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable{
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);

    private void pause() throws InterruptedException{
        if(ponderFactor == 0){
            return;
        }

        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }


    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println(this + " " + "thinking");
                pause();
                System.out.println(this + " " + "is waiting for right " + right);
                right.take();
                System.out.println(this + " " + "get right " + right);
                System.out.println(this + " " + "is waiting for left " + left);
                left.take();
                System.out.println(this + " " + "get left " + left);
                System.out.println(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
            }
        }catch (InterruptedException e){
            System.out.println(this + " " + "exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id + "(" + left + "," + right + ")";
    }
}
