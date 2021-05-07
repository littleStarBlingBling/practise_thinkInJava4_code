package com.practise.arrays;

import java.util.Arrays;
import java.util.Comparator;

class CompTypeComparator implements Comparator<CompType> {
    // 根据 j 的值进行排序
    @Override
    public int compare(CompType o1, CompType o2) {
        return Integer.compare(o1.j, o2.j);
    }
}

public class ComparatorTest {

    public static void main(String[] args) {
        CompType[] array = Generated.array(new CompType[6], CompType.generator());

        System.out.println("排序前...");

        System.out.println(Arrays.toString(array));

        System.out.println("排序后...");

        Arrays.sort(array, new CompTypeComparator());

        System.out.println(Arrays.toString(array));
    }
}
