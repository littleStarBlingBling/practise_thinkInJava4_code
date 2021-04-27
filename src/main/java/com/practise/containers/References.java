package com.practise.containers;

import java.lang.ref.*;
import java.util.LinkedList;

class VeryBig {

    private String id;

    public VeryBig(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    // 对象被回收时才会执行的方法
    @Override
    protected void finalize() {
        System.out.println("Finalizing " + id);
    }
}


public class References {

    private static ReferenceQueue<VeryBig> referenceQueue = new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<? extends VeryBig> reference = referenceQueue.poll();
        if (reference != null) {
            System.out.println("In queue: " + reference.get());
        }
    }

    public static void main(String[] args) {

        int size = 3;

        // 创建软引用、弱引用后，检查是否在 referenceQueue 中存在
        LinkedList<SoftReference<VeryBig>> softReferenceList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            softReferenceList.add(new SoftReference<>(new VeryBig("Soft " + i), referenceQueue));
            System.out.println("Just created: " + softReferenceList.getLast());
            checkQueue();
        }

        LinkedList<WeakReference<VeryBig>> weakReferenceList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            weakReferenceList.add(new WeakReference<>(new VeryBig("Weak " + i), referenceQueue));
            System.out.println("Just created: " + weakReferenceList.getLast());
            checkQueue();
        }

        // 垃圾回收只回收了虚引用
        System.out.println("\n--- 开始垃圾回收 ---\n");
        System.gc();

        // 创建虚引用后，直接就放到了 referenceQueue 中
        LinkedList<PhantomReference> phantomReferenceList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            phantomReferenceList.add(new PhantomReference<>(new VeryBig("Phantom " + i), referenceQueue));
            System.out.println("Just created: " + phantomReferenceList.getLast());
            checkQueue();
        }

    }
}
