package com.practise.containers;

import com.practise.arrays.RandomGenerator;
import com.practise.generics.Generator;

import java.util.Iterator;

class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {

    private int size = 9;
    private int number = 1;
    private char letter = 'A';

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return number < size;

            }

            @Override
            public Integer next() {
                return number++;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Pair<Integer, String> next() {
        return new Pair<>(number++, "" + letter++);
    }
}

public class MapDataTest {
    public static void main(String[] args) {
        // generator 可以同时提供 map 需要的 key 和 value
        System.out.println(MapData.map(new Letters(), 6));
        // 也可以两个 generator 分别提供 map 需要的 key 和 value
        System.out.println(MapData.map(new RandomGenerator.Character(), new RandomGenerator.String(3), 6));
        // 一个 generator 提供 key，填充同一个值
        System.out.println(MapData.map(new RandomGenerator.Character(), "Value", 6));
        // 一个 key 的迭代器，一个 generator 提供 value
        System.out.println(MapData.map(new Letters(), new RandomGenerator.String(6)));
        // 一个 key 的迭代器，填充同一个值
        System.out.println(MapData.map(new Letters(), "Hello"));
    }
}
