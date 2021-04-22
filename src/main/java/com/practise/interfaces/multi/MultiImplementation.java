package com.practise.interfaces.multi;

class D {
    public void say(){ }
}

abstract class E {
    public abstract void say();
}


class Z extends D {

    // 外围类已经有父类了，而 maveE() 方法返回的匿名内部类是抽象类 E 的子类
    E makeE() {
        return new E() {
            @Override
            public void say() {
                System.out.println("hi");
            }
        };
    }

    // 也可以返回普通类 D 的子类
    D makeD(){
        return new D(){
          @Override
          public void say(){
              System.out.println("hello");
          }
        };
    }
}

public class MultiImplementation {

    static void takesD(D d) {
        d.say();
    }

    static void takesE(E e) {
        e.say();
    }

    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
        takesD(z.makeD());
    }

}
