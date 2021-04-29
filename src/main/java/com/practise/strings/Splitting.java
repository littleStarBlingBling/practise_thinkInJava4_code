package com.practise.strings;

import java.util.Arrays;

public class Splitting {
    private static String redis = "Redis is an open source (BSD licensed) and in-memory data structure store";

    public static void split(String regex) {
        System.out.println(Arrays.toString(redis.split(regex)));
    }

    public static void main(String[] args) {
        split(" ");
        // 一个或多个非单词字符
        split("\\W+");
        // 字母 s 后面跟着一个或多个非单词字符
        split("s\\W+");
        // 以字母 s 开头，后面跟着一个或多个字母（小写的 w 表示一个单词）
        System.out.println(redis.replaceFirst("s\\w+", "apple"));
        // 匹配三个单词中的任意一个
        System.out.println(redis.replaceAll("open|data|and", "banana"));
    }
}
