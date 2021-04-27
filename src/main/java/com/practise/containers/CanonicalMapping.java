package com.practise.containers;

import java.util.Arrays;
import java.util.WeakHashMap;

class Element {
    private String id;

    public Element(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Element && id.equals(((Element) obj).id);
    }

    @Override
    protected void finalize() {
        System.out.println("Finalizing " + getClass().getSimpleName() + " " + id);
    }
}

class Key extends Element {
    public Key(String id) {
        super(id);
    }
}

class Value extends Element {
    public Value(String id) {
        super(id);
    }
}

public class CanonicalMapping {

    public static void main(String[] args) {

        int size = 30;

        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<>();

        for (int i = 0; i < size; i++) {
            Key key = new Key(Integer.toString(i));
            Value value = new Value(Integer.toString(i));
            map.put(key, value);

            // 把3的倍数放入数组中，形成不不可回收的强引用
            if (i % 3 == 0) {
                keys[i] = key;
            }
        }

        Arrays.stream(keys).forEach(p -> System.out.print(p + ","));
        System.gc();

    }
}
