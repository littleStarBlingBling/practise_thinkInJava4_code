package com.practise.containers;

import java.util.*;

public class PrintingContainers {

    static Collection fill(Collection<String> collection) {
        List<String> fruitList = Arrays.asList("apple", "banana", "banana");
        collection.addAll(fruitList);
        return collection;
    }

    static Map fill(Map<String, String> map) {
        map.put("apple", "red");
        map.put("banana", "yellow");
        map.put("banana", "green");
        return map;
    }

    public static void main(String[] args) {
        System.out.println("ArrayList：" + fill(new ArrayList<>()));
        System.out.println("LinkedList：" + fill(new LinkedList<>()));
        System.out.println("HashSet：" + fill(new HashSet<>()));
        System.out.println("TreeSet：" + fill(new TreeSet<>()));
        System.out.println("LinkedHashSet：" + fill(new LinkedHashSet<>()));
        System.out.println("HashMap：" + fill(new HashMap<>()));
        System.out.println("TreeMap：" + fill(new TreeMap<>()));
        System.out.println("LinkedHashMap：" + fill(new LinkedHashMap<>()));

    }
}
