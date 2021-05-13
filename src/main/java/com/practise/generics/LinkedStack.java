package com.practise.generics;

public class LinkedStack<T> {

    // 链表里的节点
    private static class Node<U> {
        // 节点中存储的数据
        U item;

        // 指向下一个节点
        Node<U> next;

        Node() {
            item = null;
            next = null;
        }

        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }

    }

    // 链表里的头节点
    private Node<T> top = new Node<>();

    // 将新节点增加到链表头部
    public void push(T item) {
        top = new Node<>(item, top);
    }

    // 取出头节点的数据
    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<>();
        for (String s : "Today is Sunday".split(" ")) {
            linkedStack.push(s);
        }

        String s;
        while ((s = linkedStack.pop()) != null) {
            System.out.println(s);
        }
    }
}
