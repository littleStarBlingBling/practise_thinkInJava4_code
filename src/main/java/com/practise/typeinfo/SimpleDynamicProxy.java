package com.practise.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if (args != null) {
            Arrays.stream(args).forEach(System.out::println);
        }
        // 实际使用的是构造器中传入的被代理对象，而不是 invoke 中传入的代理对象
        return method.invoke(proxied, args);
    }
}


public class SimpleDynamicProxy {

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("eat apple");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        // 直接调用
        consumer(real);
        // 插入一个代理，再次调用 consumer 方法
        // 三个入参：类加载器、要实现的接口数组、调用处理器的实例
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamicProxyHandler(real));
        consumer(proxy);
    }
}