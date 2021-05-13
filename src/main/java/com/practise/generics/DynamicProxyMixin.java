package com.practise.generics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

class MixinProxy implements InvocationHandler {

    // <方法名，代理类>
    Map<String, Object> delegateByMethod;

    // 用到了之前定义的元组工具类
    public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {

        delegateByMethod = new HashMap<>();

        // 遍历二元组对象的数组
        for (TwoTuple<Object, Class<?>> pair : pairs) {
            // 遍历 Class 对象中的 Method，取出方法名，放到 map 中
            for (Method method : pair.second.getMethods()) {
                String methodName = method.getName();
                if (!delegateByMethod.containsKey(methodName)) {
                    delegateByMethod.put(methodName, pair.first);
                }
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Object delegate = delegateByMethod.get(methodName);
        return method.invoke(delegate, args);

    }


    @SuppressWarnings("unchecked")
    public static Object newInstance(TwoTuple... pairs) {
        // 取出元组中的 Class 对象，作为动态代理要实现的接口数组
        Class[] interfaces = new Class[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            interfaces[i] = (Class) pairs[i].second;
        }

        ClassLoader classLoader = pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(classLoader, interfaces, new MixinProxy(pairs));
    }

}

public class DynamicProxyMixin {

    public static void main(String[] args) {
        // 生成代理类
        Object mixin = MixinProxy.newInstance(
                Tuple.tuple(new BasicImpl(), Basic.class),
                Tuple.tuple(new TimeStampedImpl(), TimeStamped.class),
                Tuple.tuple(new SerialNumberedImpl(), SerialNumbered.class)
        );

        // 可以转型成需要混入的各个类
        Basic basic = (Basic)mixin;
        TimeStamped timeStamped = (TimeStamped)mixin;
        SerialNumbered serialNumbered = (SerialNumbered)mixin;

        // 现在各个类都是可视的了
        basic.set("hi");
        System.out.println(basic.get());
        System.out.println(timeStamped.getStamp());
        System.out.println(serialNumbered.getSerialNumber());
    }
}
