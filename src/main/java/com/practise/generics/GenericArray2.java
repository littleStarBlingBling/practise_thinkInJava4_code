package com.practise.generics;

public class GenericArray2<T> {

    // 内部使用非泛型数组
    private Object[] array;

    public GenericArray2(int size) {
        this.array = new Object[size];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    // 在使用时才进行转型
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @SuppressWarnings(("unchecked"))
    public T[] rep() {
        return (T[]) array;
    }

    public static void main(String[] args) {
        GenericArray2<Integer> genericArray2 = new GenericArray2<>(3);
        for (int i = 0; i < 3; i++) {
            genericArray2.put(i, i);
        }

        for (int i = 0; i < 3; i++) {
            System.out.print(genericArray2.get(i) + " ");
        }
        System.out.println();

        // 但还是会有 ClassCastException
        try{
            Integer[] array = genericArray2.rep();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
