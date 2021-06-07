package com.practise.enumerated;

import com.practise.utils.EnumUtils;

public class RoShamBo {
    public static <T extends Competitor<T>> void match(T a, T b) {
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }

    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> rsbClass, int size) {
        for (int i = 0; i < size; i++) {
            match(EnumUtils.random(rsbClass), EnumUtils.random(rsbClass));
        }
    }
}
