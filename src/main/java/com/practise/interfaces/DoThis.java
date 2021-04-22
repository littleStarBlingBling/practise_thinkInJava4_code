package com.practise.interfaces;

public class DoThis {

    void f() {
        System.out.println("DoThis.f()");
    }

    // 内部类返回了外围类的引用
    public class Inner {
        public DoThis outer() {
            return DoThis.this;
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DoThis doThis = new DoThis();
        // 通过调用外围类的方法来返回内部类实例
        DoThis.Inner dti = doThis.inner();
        dti.outer().f();
    }

}
