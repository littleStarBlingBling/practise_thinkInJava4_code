package com.practise.generics;

public class GenericMethods {

    // 返回传入实例中的类名
    public <T> void f(T x){
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods genericMethods = new GenericMethods();
        genericMethods.f(" ");
        genericMethods.f(123);
    }
}
