package com.practise.containers.collection;

import java.util.AbstractCollection;
import java.util.Iterator;

class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    static Animal[] animalArray() {
        return new Animal[]{
                new Animal("dog"),
                new Animal("cat"),
                new Animal("sheep"),
                new Animal("bird"),
                new Animal("fish"),
        };
    }

    @Override
    public String toString() {
        return name;
    }
}

public class CollectionSequence extends AbstractCollection<Animal> {

    private Animal[] animals = Animal.animalArray();

    @Override
    public Iterator<Animal> iterator() {
        return new Iterator<Animal>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < animals.length;
            }

            @Override
            public Animal next() {
                return animals[index++];
            }
        };
    }

    @Override
    public int size() {
        return animals.length;
    }


    public static void main(String[] args) {
        CollectionSequence collectionSequence = new CollectionSequence();
        for(Animal animal: collectionSequence){
            System.out.print(animal + " ");
        }
    }
}
