package com.practise.arrays;

import java.util.Arrays;

public class GeneratedTest {

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4};
        System.out.println(Arrays.toString(array));

        array = Generated.array(array, new RandomGenerator.Integer());
        System.out.println(Arrays.toString(array));

        Integer[] array2 = Generated.array(Integer.class, new RandomGenerator.Integer(), 3);
        System.out.println(Arrays.toString(array2));
    }
}
