package com.practise.containers;

import java.util.Iterator;

public class IterableClass implements Iterable<String> {

    protected String[] words = "I have a red apple".split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }
        };
    }

    public static void main(String[] args) {
        for (String s : new IterableClass()) {
            System.out.print(s + " ");
        }
    }
}
