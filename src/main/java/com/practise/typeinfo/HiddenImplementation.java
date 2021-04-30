package com.practise.typeinfo;

import com.practise.typeinfo.hidden.HiddenC;
import java.lang.reflect.Method;

public class HiddenImplementation {

    public static void main(String[] args) throws Exception{

        A a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());

        // 不能编译通过
//        if(a instanceof C){
//            C c = (C)a;
//            c.g();
//        }

        // 但是可以通过反射拿到
        callHiddenMethod(a, "g");
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");

    }

    static void callHiddenMethod(Object a, String methodName) throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        // 修改访问权限
        g.setAccessible(true);
        g.invoke(a);
    }
}
