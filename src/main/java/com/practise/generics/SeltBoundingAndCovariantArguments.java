package com.practise.generics;

interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> { }

public class SeltBoundingAndCovariantArguments {

    void testA(Setter s1, Setter s2, SelfBoundSetter selfBoundSetter) {
        s1.set(s2);

//		编译错误，参数不符
//		s1.set(selfBoundSetter);
    }
}