package com.practise.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class FailFast {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        Iterator<String> iterator = collection.iterator();
        collection.add("An object");
        try {
            String s = iterator.next();
        } catch (ConcurrentModificationException e) {
            System.out.println(e);
        }
    }
}
