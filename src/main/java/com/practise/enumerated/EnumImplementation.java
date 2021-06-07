package com.practise.enumerated;

import com.practise.generics.Generator;

import java.util.Random;

enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

    private Random random = new Random(47);


    @Override
    public CartoonCharacter next() {
        return values()[random.nextInt(values().length)];
    }
}

public class EnumImplementation {
    public static <T> void printNext(Generator<T> generator) {
        System.out.print(generator.next() + ", ");
    }

    public static void main(String[] args) {
        // 必须有一个 enum 实例才能调用所需的方法
        CartoonCharacter cartoonCharacter = CartoonCharacter.BOB;
        for (int i = 0; i < 6; i++) {
            printNext(cartoonCharacter);
        }
    }
}
