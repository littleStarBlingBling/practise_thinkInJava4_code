package com.practise.generics;

class Apple{ }

// 一个泛型类
public class Holder<T> {
    private T a;

    public Holder() { }

    public Holder(T a) {
        this.a = a;
    }

    public void set(T a){
        this.a = a;
    }

    public T get(){
        return a;
    }

    public static void main(String[] args) {
        Holder<Apple> holdApple = new Holder<>(new Apple());
        Apple apple = holdApple.get();
        holdApple.set(new Apple());
    }
}
