package com.practise.containers;

import java.util.*;

public class MultiIterableClass extends IterableClass {

    // 添加反向迭代的方法
    public Iterable<String> reversed() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {

                    int current = words.length - 1;

                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public String next() {
                        return words[current--];
                    }
                };
            }
        };
    }

    // 利用集合类的 shuffle 方法改变数据顺序
    public Iterable<String> randomized() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                List<String> shuffled = new ArrayList<String>(Arrays.asList(words));
                Collections.shuffle(shuffled, new Random(20));
                return shuffled.iterator();
            }
        };
    }

    public static void main(String[] args) {
        MultiIterableClass mic = new MultiIterableClass();
        for (String s : mic.reversed()) {
            System.out.print(s + " ");
        }

        System.out.println();

        for (String s : mic.randomized()) {
            System.out.print(s + " ");
        }

        System.out.println();

        for (String s : mic) {
            System.out.print(s + " ");
        }

    }
}
