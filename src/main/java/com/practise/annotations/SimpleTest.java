package com.practise.annotations;


@Simple
public class SimpleTest {

    @Simple
    private int i;

    @Simple
    public SimpleTest() {
    }

    @Simple
    public void foo() {
        System.out.println("SimpleTest.foo()");
    }

    @Simple
    public void bar(String s, int i, float f) {
        System.out.println("SimpleTest.bar()");
    }


    @Simple
    public static void main(String[] args) {
        @Simple
        SimpleTest simpleTest = new SimpleTest();
        simpleTest.foo();
        simpleTest.bar("", 0, 0f);
    }
}
