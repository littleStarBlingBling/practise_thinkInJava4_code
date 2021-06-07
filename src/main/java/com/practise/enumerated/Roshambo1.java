package com.practise.enumerated;

import java.util.Random;
import static com.practise.enumerated.Outcome.*;

interface Item {
    // 在 compete() 方法中调用各个 Item 重载的 eval() 方法
    Outcome compete(Item item);

    // 为各个类型重载方法
    Outcome eval(Paper paper);

    Outcome eval(Scissors scissors);

    Outcome eval(Rock rock);
}

class Paper implements Item {
    // 注意这里是调用入参的 eval() 方法，会跳转到其他类
    @Override
    public Outcome compete(Item item) {
        return item.eval(this);
    }

    @Override
    public Outcome eval(Paper paper) {
        return DRAW;
    }

    // 重载方法返回的结果，是入参 VS 本类
    @Override
    public Outcome eval(Scissors scissors) {
        return WIN;
    }

    @Override
    public Outcome eval(Rock rock) {
        return LOSE;
    }

    @Override
    public String toString() {
        return "Paper";
    }
}

class Scissors implements Item {
    @Override
    public Outcome compete(Item item) {
        return item.eval(this);
    }

    @Override
    public Outcome eval(Paper paper) {
        return LOSE;
    }

    @Override
    public Outcome eval(Scissors scissors) {
        return DRAW;
    }

    @Override
    public Outcome eval(Rock rock) {
        return WIN;
    }

    @Override
    public String toString() {
        return "Scissors";
    }
}

class Rock implements Item {
    @Override
    public Outcome compete(Item item) {
        return item.eval(this);
    }

    @Override
    public Outcome eval(Paper paper) {
        return WIN;
    }

    @Override
    public Outcome eval(Scissors scissors) {
        return LOSE;
    }

    @Override
    public Outcome eval(Rock rock) {
        return DRAW;
    }

    @Override
    public String toString() {
        return "Rock";
    }
}

public class Roshambo1 {
    private static final int SIZE = 10;

    private static Random random = new Random(47);

    public static Item newItem() {
        switch (random.nextInt(3)) {
            default:
            case 0:
                return new Scissors();
            case 1:
                return new Paper();
            case 2:
                return new Rock();
        }
    }

    // 传入两个不确定的类
    public static void match(Item a, Item b) {
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            match(newItem(), newItem());
        }
    }
}
