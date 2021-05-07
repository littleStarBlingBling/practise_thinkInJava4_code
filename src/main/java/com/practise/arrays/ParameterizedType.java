package com.practise.arrays;

// 泛型类
class ClassParameter<T> {
    public T[] f(T[] arg) {
        return arg;
    }
}

// 静态泛型方法
class MethodParameter {
    public static <T> T[] f(T[] arg) {
        return arg;
    }
}

public class ParameterizedType {

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3};
        Double[] doubleArray = {1.1, 2.2, 3.3};

        // 数组变量不能声明为泛型的，但是可以持有泛型的引用
        Integer[] intArray2 = new ClassParameter<Integer>().f(intArray);
        Double[] doubleArray2 = new ClassParameter<Double>().f(doubleArray);

        intArray2 = MethodParameter.f(intArray);
        doubleArray2 = MethodParameter.f(doubleArray);
    }
}
