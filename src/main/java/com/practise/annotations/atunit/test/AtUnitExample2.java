package com.practise.annotations.atunit.test;

import java.io.FileInputStream;
import java.io.IOException;

public class AtUnitExample2 {
    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 2;
    }

    @Test
    void assertExample() {
        assert methodOne().equals("This is methodOne");
    }

    @Test
    void assertFailureExample() {
        assert 1 == 2 : "What a surprise!";
    }

    @Test
    void exceptionExample() throws IOException {
        try (
                FileInputStream in = new FileInputStream("nofile.txt")
        ) {
        }
    }

    @Test
    boolean assertAndReturn() {
        assert methodTwo() == 2 : "methodTwo must equal 2";
        return methodOne().equals("This is methodOne");
    }
}
