package com.practise.enumerated;

import java.util.Random;

// 每个输入都有相应的价格，除了最后两个
public enum Input {
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    DOLLAR(100),
    TOOTHPASTE(200),
    CHIPS(75),
    SODA(100),
    SOAP(50),
    // 两个特殊的实例，需要自定义 amount() 方法
    ABORT_TRANSACTION {
        @Override
        public int amount() {
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP {
        @Override
        public int amount() {
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    int value;

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    int amount() {
        return value;
    }

    static Random random = new Random(47);

    public static Input randomSelection() {
        // 不包括stop
        return values()[random.nextInt(values().length - 1)];
    }
}
