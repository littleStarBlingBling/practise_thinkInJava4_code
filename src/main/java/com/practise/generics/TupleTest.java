package com.practise.generics;

class Cat {
}

public class TupleTest {

    static TwoTuple<String, Integer> f() {

        return new TwoTuple<>("hi", 233);
    }

    static ThreeTuple<Cat, String, Integer> g() {
        return new ThreeTuple<>(new Cat(), "hi", 233);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> twoTuple = f();
        System.out.println(twoTuple);

        ThreeTuple<Cat, String, Integer> threeTuple = g();
        System.out.println(threeTuple);
    }
}
