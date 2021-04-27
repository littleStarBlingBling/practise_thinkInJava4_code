package com.practise.containers;

import java.util.LinkedList;

// 栈是后进先出的结构
public class Stack<T> {

    private LinkedList<T> storage = new LinkedList<>();

    // 元素的存、取、删除都在列表的头部操作
    public void push(T v) {
        storage.addFirst(v);
    }

    public T peek() {
        return storage.getFirst();
    }

    public T pop() {
        return storage.removeFirst();
    }

    public boolean empty() {
        return storage.isEmpty();
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}
