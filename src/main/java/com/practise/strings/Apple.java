package com.practise.strings;

import java.io.PrintStream;
import java.util.Formatter;

public class Apple {
    private String name;
    private Formatter formatter;

    public Apple(String name, Formatter formatter) {
        this.name = name;
        this.formatter = formatter;
    }

    public void move(int x, int y){
        formatter.format("%s The Apple is at (%d,%d)\n", name, x, y);
    }

    public static void main(String[] args) {
        PrintStream printStream = System.out;
        Apple redApple = new Apple("red", new Formatter(printStream));
        redApple.move(1, 1);
    }
}
