package com.practise.generics;

public class SelfBoundingMethods {

    // 要求传入的参数必须是 SelfBounded 的子类
    static <T extends SelfBounded<T>> T f(T arg){
        return arg.set(arg).get();
    }

    public static void main(String[] args) {
        A a = f(new A());
    }
}
