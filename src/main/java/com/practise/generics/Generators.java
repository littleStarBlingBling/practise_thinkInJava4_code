package com.practise.generics;

import java.util.ArrayList;
import java.util.Collection;

public class Generators {

    public static <T> Collection<T> fill(Collection<T> collection, Generator<T> generator, int n) {
        for (int i = 0; i < n; i++) {
            collection.add(generator.next());
        }

        return collection;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffees = fill(new ArrayList<>(), new CoffeeGenerator(), 4);

        coffees.forEach(System.out::println);

        Collection<Integer> fillNumbers = fill(new ArrayList<>(), new Fibonacci(), 8);

        fillNumbers.forEach(System.out::println);

    }
}
