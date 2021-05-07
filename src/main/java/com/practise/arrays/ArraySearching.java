package com.practise.arrays;

import com.practise.generics.Generator;

import java.util.Arrays;

public class ArraySearching {

    public static void main(String[] args) {
        Generator<Integer> generator = new RandomGenerator.Integer(233);
        int[] array = ConvertTo.primitive(Generated.array(new Integer[12], generator));
        Arrays.sort(array);

        System.out.println("排序后的数组：" + Arrays.toString(array));

        // 二分查找
        while (true) {
            int next = generator.next();
            int location = Arrays.binarySearch(array, next);
            if (location >= 0) {
                System.out.println("Location of " + next + " is " + location + ", array[" + location + "] = " + array[location]);
                break;
            }
        }
    }
}
