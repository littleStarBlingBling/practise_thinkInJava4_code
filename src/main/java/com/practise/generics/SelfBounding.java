package com.practise.generics;

class SelfBounded<T extends SelfBounded<T>> {
    T element;

    SelfBounded<T> set(T element) {
        this.element = element;
        return this;
    }

    T get() {
        return element;
    }
}

class A extends SelfBounded<A> { }

// 泛型参数只要是同一父类的就可以
class B extends SelfBounded<A> { }

class C extends SelfBounded<C> {
    // 子类的扩展方法
    C setAndGet(C arg) {
        set(arg);
        return get();
    }
}

// D 没有继承 SelfBounded
class D { }

// 编译错误，Type parameter D is not within its bound
// class E extends SelfBounded<D>{}


// 也可以不使用泛型
class F extends SelfBounded {
}

public class SelfBounding {

    public static void main(String[] args) {
        A a = new A();
        a.set(new A());

        a = a.set(new A()).get();
        a = a.get();

        C c = new C();
        c = c.setAndGet(new C());
    }
}
