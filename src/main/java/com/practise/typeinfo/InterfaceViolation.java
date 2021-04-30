package com.practise.typeinfo;

class B implements A {

    @Override
    public void f() {
        System.out.println("B");
    }

    public void g() {
    }
}

public class InterfaceViolation {

    public static void main(String[] args) {
        // 子类向上转型成父类
        A a = new B();
        // 调用子类的方法
        a.f();

        System.out.println(a.getClass().getName());

        // 还可以把父类转型成子类，调用子类的扩展方法
        if (a instanceof B) {
            B b = (B) a;
            b.g();
        }
    }
}