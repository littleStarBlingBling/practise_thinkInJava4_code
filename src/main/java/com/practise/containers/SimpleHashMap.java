package com.practise.containers;

import java.util.*;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

    private static final int SIZE = 233;

    @SuppressWarnings("unchecked")
    private LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;

        // 先计算出索引位置
        int index = Math.abs(key.hashCode() % SIZE);

        // 每个数组位置都是一个链表
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<>(key, value);

        // 如果链表上已经有相同的key，就替换对应的值，返回旧值
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }

        if (!found) {
            buckets[index].add(pair);
        }
        return oldValue;
    }

    // get 方法也是先计算出索引值，取出数组上的链表，进行遍历找值
    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }

        for (MapEntry<K, V> iPair : buckets[index]) {
            if (iPair.getKey().equals(key)) {
                return iPair.getValue();
            }
        }
        return null;
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }

            set.addAll(bucket);
        }

        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.put("sky","blue");
        map.put("grass","green");
        map.put("sun","warm");
        System.out.println(map);
    }
}
