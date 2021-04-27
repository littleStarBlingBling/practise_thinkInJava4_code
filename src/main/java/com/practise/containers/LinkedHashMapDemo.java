package com.practise.containers;

import java.util.LinkedHashMap;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // LinkedHashMap 是排序的
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(new CountingMapData(8));
        System.out.println(linkedHashMap);

        // 开启 LRU 算法
        linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.putAll(new CountingMapData(8));

        // 先只访问前 6 个元素，这样后两个元素就会出现在队列前面
        for (int i = 0; i < 6; i++) {
            linkedHashMap.get(i);
        }
        System.out.println(linkedHashMap);

        linkedHashMap.get(0);
        System.out.println(linkedHashMap);
    }
}
