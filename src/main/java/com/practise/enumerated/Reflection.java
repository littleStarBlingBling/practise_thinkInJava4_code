package com.practise.enumerated;

import com.practise.io.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

enum Explore {HERE, THERE}

public class Reflection {

    static String file = "/Users/Mydear/Documents/mine/project/practise_thinkInJava4_code/out/production/practise_thinkInJava4_code/com/practise/enumerated/Explore.class";

    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("------ Analyzing " + enumClass + " ------");
        // 实现的接口
        System.out.println("Interfaces: ");
        for (Type type : enumClass.getGenericInterfaces()) {
            System.out.println(type);
        }
        // 继承的父类
        System.out.println("Base: " + enumClass.getSuperclass());
        // 包含的方法
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<>();
        for (Method method : enumClass.getMethods()) {
            methods.add(method.getName());
        }

        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        // Explore 包含 Enum 中所有的方法
        System.out.println("\nExplore.containsAll(Enum)? " + exploreMethods.containsAll(enumMethods));
        System.out.print("Explore.removeAll(Enum): ");
        // 拿到 Explore 类的所有方法和 Enum 类的所有方法，做差集运算
        exploreMethods.removeAll(enumMethods);
        System.out.println(exploreMethods);

        // 利用 I/O 一章的工具类反编译 Explore 类
        OSExecute.command("javap " + file);
    }
}
