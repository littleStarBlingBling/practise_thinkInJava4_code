package com.practise.generics;

// 在工厂的构造器中传入 Class 对象
class ClassAsFactory<T> {
    T x;

    public ClassAsFactory(Class<T> kind) {
        try {
            x = kind.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

class Flower {
}

// 没有默认构造器的类
class Plant {
    private int id;

    public Plant(int id) {
        this.id = id;
    }
}

public class InstantiateGenericType {

    public static void main(String[] args) {

        ClassAsFactory<Flower> flower = new ClassAsFactory<>(Flower.class);
        System.out.println("ClassAsFactory<Flower> succeeded");

        try {
            ClassAsFactory<Plant> plant = new ClassAsFactory<>(Plant.class);
        } catch (Exception e) {
            System.out.println("ClassAsFactory<Plant> failed");
        }
    }
}
