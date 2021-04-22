package com.practise.concurrency;

public class Chopstick {
    private boolean taken = false;

    private int id;

    public Chopstick(int id) {
        this.id = id;
    }

    public synchronized void take() throws  InterruptedException{
        // 当 taken 为 true 时，表示正在被某个哲学家使用
        while (taken){
            wait();
        }
        taken = true;
    }

    public synchronized  void drop(){
        taken = false;
        notifyAll();
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
