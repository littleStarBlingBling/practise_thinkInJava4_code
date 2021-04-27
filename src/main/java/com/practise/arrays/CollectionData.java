package com.practise.arrays;

import com.practise.generics.Generator;

import java.util.ArrayList;

public class CollectionData<T> extends ArrayList<T> {

    // 在构造器中初始化数据集
    public CollectionData(Generator<T> generator, int quantity) {
        for (int i = 0; i < quantity; i++) {
            add(generator.next());
        }
    }

    // 通过静态方法生成实例
    public static <T> CollectionData<T> list(Generator<T> generator, int quantity) {
        return new CollectionData<>(generator, quantity);
    }
}
