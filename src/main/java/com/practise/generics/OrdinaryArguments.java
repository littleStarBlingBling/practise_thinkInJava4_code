package com.practise.generics;

class OrdinarySetter{
    void set(Base base){
        System.out.println("OrdinarySetter.set(Base)");
    }
}

class DerivedSetter extends  OrdinarySetter{
    void set(Derived derived){
        System.out.println("DerivedSetter.set(Derived)");
    }
}

public class OrdinaryArguments {

    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedSetter setter = new DerivedSetter();
        setter.set(derived);
        setter.set(base);
    }
}