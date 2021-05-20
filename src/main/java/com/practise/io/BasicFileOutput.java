package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;

public class BasicFileOutput {

    private static String file = ConstUtils.OUT_PREFIX + "BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        // 读取文件内容
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(ConstUtils.IO_PREFIX + "BasicFileOutput.java")));
        // 输出读取内容到指定文件中
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        // 打印到控制台
        System.out.println(BufferedInputFile.read(file));
    }
}