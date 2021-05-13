package com.practise.generics;

import java.lang.reflect.Method;

class SmartDog {
    public void speak() {
        System.out.println("Woof!");
    }

    public void sit() {
        System.out.println("Sitting");
    }

    public void reproduce() { }
}

class Mime {
    public void walkAgainsTheWind() { }

    public void sit() {
        System.out.println("Pretending to sit");
    }

    public void pushInvisibleWalls() { }

    @Override
    public String toString() {
        return "Mime";
    }
}

class CommunicateReflectively {

    // 通过反射来调用 speak() 和 sit() 方法
    public static void perform(Object speaker) {
        Class<?> speakerClass = speaker.getClass();
        try {
            try {
                Method speak = speakerClass.getMethod("speak");
                speak.invoke(speaker);
            } catch (NoSuchMethodException e) {
                System.out.println(speaker + " cannot speak");
            }

            try {
                Method sit = speakerClass.getMethod("sit");
                sit.invoke(speaker);
            } catch (NoSuchMethodException e) {
                System.out.println(speaker + " cannot sit");
            }
        } catch (Exception e) {
            throw new RuntimeException(speaker.toString(), e);
        }
    }
}

public class LatentReflection {

    public static void main(String[] args) {
        // 实现了 Performs 接口，同时有 speak() 和 sit() 方法
        CommunicateReflectively.perform(new Robot());
        // 没有实现 Performs 接口，同时有 speak() 和 sit() 方法
        CommunicateReflectively.perform(new SmartDog());
        // 没有实现 Performs 接口，只有 sit() 方法
        CommunicateReflectively.perform(new Mime());
    }
}
