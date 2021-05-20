package com.practise.io;

public class OSExecuteDemo {

    public static void main(String[] args) {
        String file = "/Users/Mydear/Documents/mine/project/practise_thinkInJava4_code/out/production/practise_thinkInJava4_code/com/practise/io/OSExecuteDemo.class";
        OSExecute.command("javap " + file);
    }
}
