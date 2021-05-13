package com.practise.generics;

public class CaptureConversion {

    static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }

    // 入参是未知类型，通过调用 f1() 来确定参数具体类型
    static void f2(Holder<?> holder){
        // 调用捕获类型
        f1(holder);
    }

    public static void main(String[] args) {
        // 明确类型
        Holder raw = new Holder<>(1);
        f2(raw);

        // 原生类型
        Holder rawBasic = new Holder();
        rawBasic.set(new Object());
        f2(rawBasic);

        // 使用通配符占位
        Holder<?> wildCarded = new Holder<>(1.0);
        f2(wildCarded);
    }
}
