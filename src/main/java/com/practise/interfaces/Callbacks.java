package com.practise.interfaces;

interface Incrementable {
    void increment();
}

class Callee1 implements Incrementable {
    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.out.println(i);
    }
}

class MyIncrement {
    public void increment() {
        System.out.println("Other operation");
    }

    static void f(MyIncrement myIncrement) {
        myIncrement.increment();
    }
}

class Callee2 extends MyIncrement {
    private int i = 0;

    // 子类先调用了父类的 increment() 方法
    @Override
    public void increment() {
        super.increment();
        i++;
        System.out.println(i);
    }

    // 在内部类的 increment() 方法中调用了外围类的 increment() 方法
    private class Closure implements Incrementable {
        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }

    // 外围类的方法返回了内部类的实例
    Incrementable getCallbackReference(){
        return new Closure();
    }
}

class Caller {
    private Incrementable callbackReference;

    Caller(Incrementable callbackReference) {
        this.callbackReference = callbackReference;
    }

    // 在这里传入 Closure 的引用，实际调用的是 Callee2 父类和本身的 increment() 方法
    void go() {
        callbackReference.increment();
    }
}


public class Callbacks {
    public static void main(String[] args) {
        Callee1 callee1 = new Callee1();
        Callee2 callee2 = new Callee2();
        // 这里传入了子类实例，会先调用父类的 increment() 然后执行子类 increment()
        MyIncrement.f(callee2);

        Caller caller1 = new Caller(callee1);
        // 外围类返回了内部类的引用 -> 内部类调用外围类的方法 -> ABA
        Caller caller2 = new Caller(callee2.getCallbackReference());

        // caller1 只是执行自己的 increment() 方法
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}
