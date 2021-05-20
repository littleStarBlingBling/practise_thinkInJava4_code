package com.practise.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class House implements Serializable { }

class Animal implements Serializable {
    private String name;
    private House preferredHouse;

    public Animal(String name, House preferredHouse) {
        this.name = name;
        this.preferredHouse = preferredHouse;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() + "], " + preferredHouse + "\n";
    }
}

public class MyWorld {

    public static void main(String[] args) throws Exception {
        House house = new House();
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("dog", house));
        animalList.add(new Animal("cat", house));
        animalList.add(new Animal("fish", house));

        // 把 animalList 写入到两个 OutputStream 中
        System.out.println("animals: " + animalList);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buffer);
        out.writeObject(animalList);
        // 在 out 中写入两个 animalList
        out.writeObject(animalList);

        ByteArrayOutputStream buffer2 = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(buffer2);
        out2.writeObject(animalList);

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buffer2.toByteArray()));
        List
                animalList1 = (List) in.readObject(),
                animalList2 = (List) in.readObject(),
                animalList3 = (List) in2.readObject();

        System.out.println("animalList1: " + animalList);
        System.out.println("animalList2: " + animalList);
        System.out.println("animalList3: " + animalList);
    }
}
