package com.practise.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

interface Addable<T> {
    void add(T t);
}

public class Fill2 {

    // 用反射添加元素
    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++) {
            try {
                addable.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 用生成器添加元素
    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        for (int i = 0; i < size; i++) {
            addable.add(generator.next());
        }
    }
}

// 适配器的实现类
class AddableCollectionAdapter<T> implements Addable<T> {

    private Collection<T> collection;

    public AddableCollectionAdapter(Collection<T> collection) {
        this.collection = collection;
    }

    @Override
    public void add(T item) {
        collection.add(item);
    }
}

// 一个自动捕获类型的工具
class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> collection) {
        return new AddableCollectionAdapter<>(collection);
    }
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
    @Override
    public void add(T item) {
        super.add(item);
    }
}

class Fill2Test {

    public static void main(String[] args) {
        // 有现成迭代器的 ArrayList
        List<Coffee> carrier = new ArrayList<>();
        Fill2.fill(new AddableCollectionAdapter<>(carrier), Coffee.class, 3);
        Fill2.fill(new AddableCollectionAdapter<>(carrier), Cappuccino.class, 2);

        for(Coffee coffee : carrier){
            System.out.println(coffee);
        }

        // 自己实现迭代器的队列
        AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<>();
        Fill2.fill(coffeeQueue, Cappuccino.class, 3);
        Fill2.fill(coffeeQueue, Mocha.class, 2);

        for(Coffee coffee : coffeeQueue){
            System.out.println(coffee);
        }

    }
}
