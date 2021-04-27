package com.practise.containers;

import com.practise.generics.Generator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class QueueBehavior {
    private static int count = 10;

    static <T> void test(Queue<T> queue, Generator<T> generator) {
        // 先添加数据
        for (int i = 0; i < count; i++) {
            queue.offer(generator.next());
        }

        // 遍历打印队列中的内容
        while (queue.peek() != null) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }

    // 队列内容生成器
    static class Gen implements Generator<String> {
        String[] s = "one two three four five six seven eight nine ten".split(" ");

        int i;

        @Override
        public String next() {
            return s[i++];
        }
    }

    public static void main(String[] args) {
        test(new LinkedList<>(), new Gen());
        test(new PriorityQueue<>(), new Gen());
        test(new ArrayBlockingQueue<>(count), new Gen());
        test(new ConcurrentLinkedQueue<>(), new Gen());
        test(new PriorityBlockingQueue<>(), new Gen());
    }
}
