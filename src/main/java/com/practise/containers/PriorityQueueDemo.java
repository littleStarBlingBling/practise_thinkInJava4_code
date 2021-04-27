package com.practise.containers;

import java.util.*;

public class PriorityQueueDemo {

    public static void main(String[] args) {
        // 随机生成 5 个数，在优先级队列中自动按数字大小排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Random random = new Random(20);
        for (int i = 0; i < 5; i++) {
            priorityQueue.offer(random.nextInt(i + 10));
        }
        QueueDemo.printQueue(priorityQueue);

        // 正序和逆序输出列表
        List<Integer> integerList = Arrays.asList(20, 15, 10, 5, 0);
        priorityQueue = new PriorityQueue<>(integerList);
        QueueDemo.printQueue(priorityQueue);

        priorityQueue = new PriorityQueue<>(integerList.size(), Collections.reverseOrder());
        priorityQueue.addAll(integerList);
        QueueDemo.printQueue(priorityQueue);

        // 字符串按字母排序
        String apple = "A red apple";
        List<String> appleString = Arrays.asList(apple.split(""));

        PriorityQueue<String> stringPQ = new PriorityQueue<>(appleString);
        QueueDemo.printQueue(stringPQ);

        stringPQ = new PriorityQueue<>(appleString.size(), Collections.reverseOrder());
        stringPQ.addAll(appleString);
        QueueDemo.printQueue(stringPQ);

        // 用HashSet来除重
        Set<Character> charSet = new HashSet<>();
        for(char c : apple.toCharArray()){
            charSet.add(c);
        }

        PriorityQueue<Character> characterPQ = new PriorityQueue<>(charSet);
        QueueDemo.printQueue(characterPQ);
    }
}
