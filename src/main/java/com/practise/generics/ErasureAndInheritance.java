package com.practise.generics;

class GenericBase<T> {
    private T element;

    public T get() {
        return element;
    }

    public void set(T element) {
        this.element = element;
    }
}

// 泛型子类
class Derived1<T> extends GenericBase<T> { }

// 非泛型子类
class Derived2 extends GenericBase { }

// 下面的这两种方式都是编译器不允许的
//class Derived3 extends GenericBase<T> { }
//
//class Derived4 extends GenericBase<?> { }


public class ErasureAndInheritance {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 derived2 = new Derived2();
        Object object = derived2.get();
        derived2.set(object);
    }
}
