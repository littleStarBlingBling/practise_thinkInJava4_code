package com.practise.containers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QueueDemo {
    // 打印队列
    public static void printQueue(Queue queue) {
        while (queue.peek() != null) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Random random = new Random(20);
        for (int i = 0; i < 10; i++) {
            queue.offer(random.nextInt(i + 10));
        }

        printQueue(queue);

        Queue<Character> characterQueue = new LinkedList<>();
        for(char c : "Orange".toCharArray()){
            characterQueue.offer(c);
        }
        printQueue(characterQueue);
    }
}
