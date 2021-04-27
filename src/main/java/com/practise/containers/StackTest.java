package com.practise.containers;

public class StackTest {

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        for(String s : "My dog is pretty cute".split(" ")){
            stack.push(s);
        }

        while(!stack.empty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
