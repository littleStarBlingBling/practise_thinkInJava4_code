package com.practise.annotations.atunit.test;

public class AtUnitExample1 {

    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.print("This is methodTwo");
        return 2;
    }

    @Test
    boolean methodOneTest() {
        return methodOne().endsWith("This is methodOne");
    }

    @Test
    boolean m2() {
        return methodTwo() == 2;
    }

    @Test
    private boolean m3() {
        return true;
    }

    // 展示失败的输出
    @Test
    boolean failureTest() {
        return false;
    }

    @Test
    boolean anotherDisappointment() {
        return false;
    }
}
