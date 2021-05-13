package com.practise.generics;

// Dog 和 Robot 都实现了 Performs 接口，也有自己的扩展方法
class Dog implements Performs {

    @Override
    public void speak() {
        System.out.println("Woof!");
    }

    @Override
    public void sit() {
        System.out.println("Sitting");
    }

    public void reproduce() { }
}

class Robot implements Performs {

    @Override
    public void speak() {
        System.out.println("Click!");
    }

    @Override
    public void sit() {
        System.out.println("Clank!");
    }

    public void oilChange() { }
}

// 使用这两个实现类
class Communicate {
    public static <T extends Performs> void perform(T performer) {
        performer.speak();
        performer.sit();
    }
}

public class DogsAndRobots {

    public static void main(String[] args) {
        Dog dog = new Dog();
        Robot robot = new Robot();
        Communicate.perform(dog);
        Communicate.perform(robot);
    }
}