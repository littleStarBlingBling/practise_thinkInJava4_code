package com.practise.generics;

import java.lang.reflect.Array;

public class GenericArrayWithTypeToken<T> {

    // 这里用的是泛型数组
    private T[] array;

    // 在构造器中获得了具体的类型
    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int size) {
        array = (T[]) Array.newInstance(type, size);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> ga = new GenericArrayWithTypeToken<>(Integer.class, 3);
        Integer[] array = ga.rep();
    }
}
