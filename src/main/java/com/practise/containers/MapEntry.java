package com.practise.containers;

import java.util.Map;

public class MapEntry<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    // 设置新值，返回旧值
    @Override
    public V setValue(V v) {
        V result = value;
        value = v;
        return result;
    }

    // 通过 key 和 value 计算 hashCode
    @Override
    public int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    // 比对 key 和 value 是否相同
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MapEntry)) {
            return false;
        }
        MapEntry me = (MapEntry) o;
        return (key == null ? me.getKey() == null : key.equals(me.getKey()))
                && (value == null ? me.getValue() == null : value.equals(me.getValue()));
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
