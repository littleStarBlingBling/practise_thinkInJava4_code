package com.practise.generics;

class Generic<T> {
    private T data;

    public Generic() {
    }

    public Generic(T data) {
        this.data = data;
    }
}

public class ArrayOfGenericReference {
    // 泛型类数组
    static Generic<Integer>[] integerArray;

    public static void main(String[] args) {

        // Unchecked assignment，编译器不能保证 Gen[] 里的每个值都是 Gen<Integer>
        integerArray = new Generic[2];

        // Generic array creation，启用了类型检查的编译器直接禁止创建泛型数组
        // integerArray = new Generic<Integer>[2];

    }
}
