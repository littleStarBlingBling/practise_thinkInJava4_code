package com.practise.generics;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayMaker<T> {

    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringArrayMaker = new ArrayMaker<>(String.class);
        String[] stringArray = stringArrayMaker.create(3);
        System.out.println(Arrays.toString(stringArray));
    }

}
