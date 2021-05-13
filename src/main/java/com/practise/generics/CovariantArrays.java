package com.practise.generics;

class Food { }

class Meat extends Food { }

class Fruit extends Food { }

class Peach extends Fruit { }

public class CovariantArrays {

    public static void main(String[] args) {
        // 向上转型
        Food[] food = new Fruit[2];
        // 可以添加 Fruit 及其子类对象
        food[0] = new Fruit();
        food[1] = new Peach();

        // 但是不能添加父类及兄弟类对象
        try{
            food[0] = new Food();
        }catch (Exception e){
            System.out.println(e);
        }

        try{
            food[0] = new Meat();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
