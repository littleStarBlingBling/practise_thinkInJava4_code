package com.practise.arrays;

import com.practise.generics.Generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CompType implements Comparable<CompType> {

    int i, j;

    private static int count = 1;

    public CompType(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        String result = "[i = " + i + ", j = " + j + "]";
        // 三个一换行
        if (count++ % 3 == 0){
            result += "\n";
        }
        return result;
    }

    // 根据 i 值排序
    @Override
    public int compareTo(CompType o) {
        return Integer.compare(i, o.i);
    }

    private static Random random = new Random(47);

    public static Generator<CompType> generator() {
        // 方法内部的匿名类
        return new Generator<CompType>() {
            @Override
            public CompType next() {
                return new CompType(random.nextInt(100), random.nextInt(100));
            }
        };
    }

    public static void main(String[] args) {
        CompType[] array = Generated.array(new CompType[6], generator());

        System.out.println("排序之前...");

        System.out.println(Arrays.toString(array));

        Arrays.sort(array);

        System.out.println("排序之后...");

        System.out.println(Arrays.toString(array));

        System.out.println("翻转之后...");

        Arrays.sort(array, Collections.reverseOrder());

        System.out.println(Arrays.toString(array));
    }
}
