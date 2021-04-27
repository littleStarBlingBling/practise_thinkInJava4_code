package com.practise.containers;

import com.practise.arrays.CollectionData;
import com.practise.generics.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

class Animal implements Generator<String> {

    private String[] small = "This cat is a kind of small animal".split(" ");

    private int index;

    @Override
    public String next() {
        return small[index++];
    }
}

public class CollectionDataTest {
    public static void main(String[] args) {
        // 通过构造器生成数据
        Set<String> set = new LinkedHashSet<>(new CollectionData<>(new Animal(), 8));
        // 通过静态方法生成数据
        set.addAll(CollectionData.list(new Animal(), 8));
        System.out.println(set);
    }
}
