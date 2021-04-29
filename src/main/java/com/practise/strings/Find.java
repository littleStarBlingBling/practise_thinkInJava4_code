package com.practise.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {

    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("\\w+").matcher("Apple is red");

        while (matcher.find()) {
            System.out.print(matcher.group() + " ");
        }

        System.out.println();
        int i = 0;
        while (matcher.find(i)) {
            System.out.print(matcher.group() + " ");
            i++;
        }
    }
}
