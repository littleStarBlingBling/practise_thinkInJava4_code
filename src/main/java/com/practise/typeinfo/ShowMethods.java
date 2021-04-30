package com.practise.typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class ShowMethods {

    private static Pattern pattern = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {

        try {
            Class<?> c = Class.forName("com.practise.typeinfo.ShowMethods");
            Method[] methods = c.getMethods();
            Constructor[] constructors = c.getConstructors();

            for (Method method : methods) {
                // 用 toString() 方法生成一个包含完整的方法特征签名的字符串，并使用正则去掉了命名修饰词部分
                System.out.println(
                        pattern.matcher(method.toString()).replaceAll("")
                );
            }


            for (Constructor cons : constructors) {
                System.out.println(
                        pattern.matcher(cons.toString()).replaceAll("")
                );
            }

        } catch (
                ClassNotFoundException e) {
            System.out.println("No such class:" + e);
        }
    }
}
