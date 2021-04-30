package com.practise.typeinfo;

public class GenericClassReferences {
    public static void main(String[] args) {
        // 同一个变量，可以指向不同类型的类引用
        Class intClass = int.class;
        intClass = double.class;

        // 但是加了泛型的类，不能指向不同类型的引用
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class;
//		genericIntClass = double.class;

        // 可以通过 ？ 这个通配符来实现
        Class<?> commonClass = int.class;
        commonClass = double.class;

        // 也可以通过界定泛型上界来实现
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
    }
}
