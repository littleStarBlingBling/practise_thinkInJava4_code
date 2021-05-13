package com.practise.generics;

abstract class GenericWithCreate<T> {
    final T element;

    public GenericWithCreate() {
        this.element = create();
    }

    abstract T create();
}

class X { }

class Creator extends GenericWithCreate<X> {

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }

    @Override
    X create() {
        return new X();
    }
}

public class CreatorGeneric {
    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.f();
    }
}
