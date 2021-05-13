package com.practise.generics;

class Base { }

class Derived extends Base {
}

interface OrdinaryGetter {
    Base get();
}

interface DerivedGetter extends OrdinaryGetter {
    // 重写方法的返回值是可以变化的，但两个类型必须有继承关系
    @Override
    Derived get();
}

public class CovariantReturnTypes {

    void test(DerivedGetter derivedGetter) {
        Derived derived = derivedGetter.get();
        Base base = derivedGetter.get();
    }
}
