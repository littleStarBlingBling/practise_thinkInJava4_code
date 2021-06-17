package com.practise.annotations.atunit.test;

public class AuComposition {

    AtUnitExample1 testObject = new AtUnitExample1();

    @Test
    boolean tMethodOne() {
        return testObject.methodOne().equals("This is methodOne");
    }

    @Test
    boolean tMethodTwo(){
        return testObject.methodTwo() == 2;
    }
}
