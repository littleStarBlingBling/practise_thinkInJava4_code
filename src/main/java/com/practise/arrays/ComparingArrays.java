package com.practise.arrays;

import java.util.Arrays;

public class ComparingArrays {

    public static void main(String[] args) {
        int[] array1 = new int[6];
        int[] array2 = new int[6];

        Arrays.fill(array1, 2);
        Arrays.fill(array2, 3);

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        System.out.println(Arrays.equals(array1, array2));
    }
}
