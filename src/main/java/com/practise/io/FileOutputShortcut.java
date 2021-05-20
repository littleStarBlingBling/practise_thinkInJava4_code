package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

public class FileOutputShortcut {

    private static String file = ConstUtils.OUT_PREFIX + "FileOutputShortcut.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(ConstUtils.IO_PREFIX + "FileOutputShortcut.java")));
        // 直接传入文件路径
        PrintWriter out = new PrintWriter(file);

        String s;
        int lineCount = 1;

        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
    }
}
