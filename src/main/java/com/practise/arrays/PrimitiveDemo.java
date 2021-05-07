package com.practise.arrays;

import java.util.Arrays;

public class PrimitiveDemo {

    public static void main(String[] args) {
        Integer[] array = Generated.array(Integer.class, new RandomGenerator.Integer(), 2);

        int[] array2 = ConvertTo.primitive(array);
        System.out.println(Arrays.toString(array2));

        boolean[] array3 = ConvertTo.primitive(Generated.array(Boolean.class, new RandomGenerator.Boolean(), 2));
        System.out.println(Arrays.toString(array3));
    }
}
