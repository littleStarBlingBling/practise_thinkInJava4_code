package com.practise.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<>();
    // 用来跟踪被签出的对象
    private volatile boolean[] checkedOut;
    private Semaphore available;

    // 使用 newInstance() 来把对象加载到池中
    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                items.add(classObject.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 调用checkOut() 来获得一个新对象
    // 如果池中没有更多的对象了，available 将阻塞调用过程
    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }

    // 使用完对象后，交给 checkIn()
    public void checkIn(T x) {
        if (releaseItem(x)) {
            available.release();
        }
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }

        return null;
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if(index == -1){
            return false;
        }

        if(checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }

        return false;
    }
}
