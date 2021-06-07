package com.practise.utils;

import java.util.Random;

public class EnumUtils {

    private static Random random = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> enumClass) {
        return random(enumClass.getEnumConstants());
    }

    public static <T> T random(T[] values){
        return values[random.nextInt(values.length)];
    }
}
