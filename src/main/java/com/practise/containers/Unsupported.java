package com.practise.containers;

import java.util.*;

public class Unsupported {
    static void test(String msg, List<String> list) {

        System.out.println("--- " + msg + " ---");

        Collection<String> collection = list;
        Collection<String> subList = list.subList(1, 8);
        Collection<String> collection2 = new ArrayList<>(subList);

        try {
            collection.retainAll(collection2);
        } catch (Exception e) {
            System.out.println("retainAll(): " + e);
        }

        try {
            collection.removeAll(collection2);
        } catch (Exception e) {
            System.out.println("removeAll(): " + e);
        }

        try {
            collection.clear();
        } catch (Exception e) {
            System.out.println("clear(): " + e);
        }

        try {
            collection.add("X");
        } catch (Exception e) {
            System.out.println("add(): " + e);
        }

        try {
            collection.addAll(collection2);
        } catch (Exception e) {
            System.out.println("addAll(): " + e);
        }

        try {
            collection.remove("C");
        } catch (Exception e) {
            System.out.println("remove(): " + e);
        }

        try {
            list.set(0, "X");
        } catch (Exception e) {
            System.out.println("List.set(): " + e);
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
        // 在使用 ArrayList 时，都是正常的
        test("Modifiable Copy", new ArrayList<>(list));
        // 传入 List 类型，就会报未获支持的操作
        test("Arrays.asList()", list);
        // 不可修改的 list，也会报未获支持的操作
        test("UnmodifiableList()", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}
