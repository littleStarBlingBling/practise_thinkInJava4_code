package com.practise.interfaces;

class WithInner{
    class Inner{}
}

public class InheritInner extends WithInner.Inner{
    // InheritInner 继承了内部类，在构造器中需要传入这个内部类的外围类的引用，并调用外围类的构造方法
    InheritInner(WithInner wi){
        wi.super();
    }

    public static void main(String[]args){
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}
