package com.practise.containers.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    static List<Animal> arrayList() {
        return new ArrayList<>(Arrays.asList(
                new Animal("dog"),
                new Animal("cat"),
                new Animal("sheep"),
                new Animal("bird"),
                new Animal("fish")
        ));
    }

    @Override
    public String toString() {
        return name;
    }
}

public class SimpleIteration {

    public static void main(String[] args) {
        List<Animal> animalList = Animal.arrayList();

        // 用迭代器
        Iterator<Animal> it = animalList.iterator();
        while (it.hasNext()) {
            Animal animal = it.next();
            System.out.print(animal + " ");
        }

        System.out.println();

        // 用 foreach
        for (Animal animal : animalList) {
            System.out.print(animal + " ");
        }

        System.out.println();

        // 在遍历的时候删除前三个元素
        it = animalList.iterator();
        for (int i = 0; i < 3; i++) {
            it.next();
            it.remove();
        }

        System.out.println(animalList);

    }
}
