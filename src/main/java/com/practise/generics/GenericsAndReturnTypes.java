package com.practise.generics;

// 基类
interface GenericGetter<T extends GenericGetter<T>> {
    T get();
}

// 导出类
interface Getter extends GenericGetter<Getter> { }

public class GenericsAndReturnTypes {

    // 既能返回子类实例，也能返回基类实例
    void test(Getter getter) {
        Getter result = getter.get();
        GenericGetter genericGetter = getter.get();
    }
}
