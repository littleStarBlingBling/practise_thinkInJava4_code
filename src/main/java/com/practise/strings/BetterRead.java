package com.practise.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetterRead {

    public static void main(String[] args) {
        String input =
                "java has regex\n"
                        + "JAVA has pretty good regular expression\n"
                        + "Regular expressions are in Java\n";

        Pattern pattern = Pattern.compile("(^java)|(java$)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
