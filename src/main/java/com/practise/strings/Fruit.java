package com.practise.strings;

public class Fruit {
    public static void main(String[] args) {
        for(String pattern : new String[]{
                "Fruit",
                "[fF]ruit",
                "[fF][rabbit][a-z].*",
                "F.*"
        }){
            System.out.println("Fruit".matches(pattern));
        }
    }
}
