package com.practise.generics;

class Fish { }

public class TupleTest2 {

    static TwoTuple<String, Integer> f() {
        return Tuple.tuple("hi", 233);
    }

    static ThreeTuple<Fish, String, Integer> g() {
        return Tuple.tuple(new Fish(), "hi", 233);
    }

    public static void main(String[] args) {
        System.out.println(f());
        System.out.println(g());
    }
}
