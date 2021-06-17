package com.practise.annotations.atunit.test;

import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T v) {
        list.addFirst(v);
    }

    public T top(){
        return list.getFirst();
    }

    public T pop(){
        return list.removeFirst();
    }
}
