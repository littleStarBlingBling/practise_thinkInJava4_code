package com.practise.strings;

import com.practise.io.TextFile;
import com.practise.utils.ConstUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JGrep {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("\\b[Ssct]\\w+");
        int index = 0;
        Matcher matcher = pattern.matcher("");
        for (String line : new TextFile(ConstUtils.STRING_PREFIX + "JGrep.java")) {
            matcher.reset(line);
            while (matcher.find()) {
                System.out.println(index++ + ": " + matcher.group() + ": " + matcher.start());
            }
        }
    }
}
