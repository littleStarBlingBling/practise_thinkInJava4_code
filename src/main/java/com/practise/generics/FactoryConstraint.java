package com.practise.generics;

// 泛型工厂接口
interface Factory<T> {
    T create();
}

// 泛型接口的实现类
class IntegerFactory implements Factory<Integer>{

    @Override
    public Integer create() {
        return 0;
    }
}

// 静态内部类实现了泛型工厂，并且返回外围类的实例
class Widget{
    public static class FactoryImpl implements Factory<Widget>{

        @Override
        public Widget create() {
            return new Widget();
        }
    }
}

class Foo<T> {
    private T x;

    // 限制入参是 Factory 的子类
    public <F extends Factory<T>> Foo(F factory) {
        x = factory.create();
    }
}



public class FactoryConstraint {

    public static void main(String[] args) {
        new Foo<Integer>(new IntegerFactory());
        new Foo<Widget>(new Widget.FactoryImpl());
    }
}
