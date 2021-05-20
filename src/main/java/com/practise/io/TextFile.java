package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
    // 读取文件内容
    public static String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            try {
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                    stringBuilder.append("\n");
                }
            } finally {
                bufferedReader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }


    // 读取文件中的内容，并根据分隔符进行拆分，存到 list 中
    public TextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
    }

    public TextFile(String fileName) {
        this(fileName, "\n");
    }

    // 将文本写入文件中
    public static void write(String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                // 遍历列表中存的数据，进行输出
                for (String item : this) {
                    out.println(item);
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        // 读取 TextFile.java 的内容并写入到 test.txt 中
        String file = read(ConstUtils.IO_PREFIX + "TextFile.java");
        write(ConstUtils.OUT_PREFIX + "test.txt", file);

        // 把 test.txt 的内容写入到 test2.txt 中
        TextFile textFile = new TextFile(ConstUtils.OUT_PREFIX + "test.txt");
        textFile.write(ConstUtils.OUT_PREFIX + "test2.txt");

        // 把 TextFile.java 中的内容，用表示非单词的正则分隔，然后打印出 TreeSet 中单词 a 之前的内容
        TreeSet<String> words = new TreeSet<>(new TextFile(ConstUtils.IO_PREFIX + "TextFile.java", "\\W+"));
        System.out.println(words.headSet("a"));

    }
}