package com.practise.typeinfo;

interface Interface{
    void doSomething();
    void somethingElse(String arg);
}


// 被代理对象与代理对象需要继承同样的接口
class RealObject implements Interface{

    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}

// 代理对象持有被代理对象实现的接口实例
class SimpleProsy implements Interface{

    private Interface proxied;

    public SimpleProsy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxied.doSomething();

    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);

    }
}

public class SimpleProxyDemo {

    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("drink");
    }


    public static void main(String[] args) {
        // 使用被代理对象
        consumer(new RealObject());
        // 使用代理对象作为中介
        consumer(new SimpleProsy(new RealObject()));
    }
}
