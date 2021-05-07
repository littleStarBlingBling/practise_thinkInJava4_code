package com.practise.arrays;

import com.practise.generics.Generator;

public class Generated {

    // 在传入的数组引用中，生成新的数据列表，并转换为数组
    public static <T> T[] array(T[] array, Generator<T> generator) {
        return new CollectionData<>(generator, array.length).toArray(array);
    }

    //根据传入的类型，生成对应类型的数组，
    @SuppressWarnings("unchecked")
    public static <T> T[] array(Class<T> type, Generator<T> generator, int size) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(type, size);
        return new CollectionData<>(generator, size).toArray(array);
    }
}
