package com.practise.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resetting {

    public static void main(String[] args) {

        Matcher matcher = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");

        while (matcher.find()){
            System.out.print(matcher.group() + " ");
        }

        System.out.println();

        matcher.reset("fix the rig with rags");
        while (matcher.find()){
            System.out.print(matcher.group() + " ");
        }
    }
}
