package com.practise.generics;

public class GenericArray<T> {

    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArray(int size) {
        array = (T[]) new Object[size];
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
        GenericArray<Integer> genericArray = new GenericArray<>(10);
        // 可以编译，但是会报ClassCastException
//        Integer[] array = genericArray.rep();
        // 正常使用
        Object[] array2 = genericArray.rep();
    }
}