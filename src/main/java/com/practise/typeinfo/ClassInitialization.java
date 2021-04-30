package com.practise.typeinfo;

import java.util.Random;

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.random.nextInt(1000);

    static {
        System.out.println("Initializing Initable");
    }

}

class Initable2{
    static int staticNonFinal = 147;
    static{
        System.out.println("Initializing Initable2");
    }
}

class Initable3{
    static int staticNonFinal = 74;
    static{
        System.out.println("Initializing Initable3");
    }
}


public class ClassInitialization {

    public static Random random = new Random(47);

    public static void main(String[] args) throws ClassNotFoundException {

        // 直接使用 .class 不会触发初始化
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");

        // static final 修饰的编译期常量，不用初始化类也可以直接访问
        System.out.println(Initable.staticFinal);

        // static final 修饰，但是不是编译期常量，会触发类初始化
        System.out.println(Initable.staticFinal2);

        // 没有 final 修饰的 static 变量会触发初始化
        System.out.println(Initable2.staticNonFinal);

        // 通过 Class.forName 也会触发类加载
        Class initable3 = Class.forName("com.practise.typeinfo.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }

}
