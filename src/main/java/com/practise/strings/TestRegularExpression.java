package com.practise.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression {

    private static String regex = "abcabcabcdefabc";
    private static String[] expression = {"abc+", "(abc)+", "(abc){2,}"};

    public static void main(String[] args) {

        for (String exp : expression) {
            System.out.println("---------");
            System.out.println("Regular expression: " + exp);
            Pattern pattern = Pattern.compile(exp);
            Matcher matcher = pattern.matcher(regex);
            while (matcher.find()) {
                System.out.println("Match \"" + matcher.group() + "\" at positions " + matcher.start() + "-" + (matcher.end() - 1));
            }
        }
    }
}
