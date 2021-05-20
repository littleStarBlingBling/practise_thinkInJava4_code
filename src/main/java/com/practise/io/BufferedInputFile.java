package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {

    // 读取指定文件中的内容
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s = in.readLine()) != null) {
            // readLine()方法会删除换行符，所以需要自己添加
            stringBuilder.append(s).append("\n");
        }

        // 关闭文件
        in.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read(ConstUtils.IO_PREFIX + "BufferedInputFile.java"));
    }
}
