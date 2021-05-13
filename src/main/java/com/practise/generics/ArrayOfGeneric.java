package com.practise.generics;

public class ArrayOfGeneric {

    static final int SIZE = 100;

    // 泛型数组类
    static Generic<Integer>[] generics;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
//		// 可以编译，但是运行会报 ClassCastException
//		generics = (Generic<Integer>[]) new Object[SIZE];

        // 通过非泛型数组强转
        generics = (Generic<Integer>[]) new Generic[SIZE];
        System.out.println(generics.getClass().getSimpleName());
        // 可以给数组中的值赋值泛型实例
        generics[0] = new Generic<>();

//		// 编译出错，类型不匹配
//		generics[1] = new Object();
//		// 编译出错，类型不匹配
//		generics[2] = new Generic<Double>();
    }
}
