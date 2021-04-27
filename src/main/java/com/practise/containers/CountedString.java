package com.practise.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountedString {

    private static List<String> created = new ArrayList<>();

    private String s;

    private int id = 0;

    public CountedString(String s) {
        this.s = s;
        created.add(s);
        for (String s2 : created) {
            if (s2.equals(s)) {
                id++;
            }
        }
    }

    @Override
    public String toString() {
        return "String: " + s + " id: " + id + " hashCode(): " + hashCode();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CountedString && s.equals(((CountedString) obj).s) && id == ((CountedString) obj).id;
    }

    public static void main(String[] args) {
        Map<CountedString, Integer> map = new HashMap<>();
        CountedString[] countedStrings = new CountedString[5];

        // 先来生成五个数据放在 map 中
        for (int i = 0; i < countedStrings.length; i++) {
            countedStrings[i] = new CountedString("hi");
            map.put(countedStrings[i], i);
        }
        System.out.println(map);

        // 遍历数组，从 map 中找出数据
        for (CountedString countedString : countedStrings) {
            System.out.println("Looking up " + countedString);
            System.out.println(map.get(countedString));
        }
    }
}
