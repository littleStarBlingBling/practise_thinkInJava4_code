package com.practise.containers;

import com.practise.generics.Generator;

import java.util.LinkedHashMap;

public class MapData<K, V> extends LinkedHashMap<K, V> {

    // 五个构造器，使用不同的入参来填充 Map
    // generator 可以同时提供 map 需要的 key 和 value
    public MapData(Generator<Pair<K, V>> generator, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Pair<K, V> pair = generator.next();
            put(pair.key, pair.value);
        }
    }

    // 也可以两个 generator 分别提供 map 需要的 key 和 value
    public MapData(Generator<K> generatorKey, Generator<V> generatorValue, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(generatorKey.next(), generatorValue.next());
        }
    }

    // 一个 generator 提供 key，填充同一个值
    public MapData(Generator<K> generatorKey, V value, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(generatorKey.next(), value);
        }
    }

    // 一个 key 的迭代器，一个 generator 提供 value
    public MapData(Iterable<K> generatorKey, Generator<V> generatorValue) {
        for (K key : generatorKey) {
            put(key, generatorValue.next());
        }
    }

    // 一个 key 的迭代器，填充同一个值
    public MapData(Iterable<K> generatorKey, V value) {
        for (K key : generatorKey) {
            put(key, value);
        }
    }

    // 向外提供对应构造器的 static 方法
    public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> generatorPair, int quantity) {
        return new MapData<>(generatorPair, quantity);
    }

    public static <K, V> MapData<K, V> map(Generator<K> generatorKey, Generator<V> generatorValue, int quantity) {
        return new MapData<>(generatorKey, generatorValue, quantity);
    }

    public static <K, V> MapData<K, V> map(Generator<K> generatorKey, V value, int quantity) {
        return new MapData<>(generatorKey, value, quantity);
    }

    public static <K, V> MapData<K, V> map(Iterable<K> generatorKey, Generator<V> generatorValue) {
        return new MapData<>(generatorKey, generatorValue);
    }

    public static <K, V> MapData<K, V> map(Iterable<K> generatorKey, V value) {
        return new MapData<>(generatorKey, value);
    }
}
