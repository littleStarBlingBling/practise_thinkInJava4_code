package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.IOException;
import java.io.RandomAccessFile;

public class UsingRandomAccessFile {

    private static String file = ConstUtils.OUT_PREFIX + "readTest.dat";

    private static void display() throws IOException {
        // r: 只读
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        for (int i = 0; i < 3; i++) {
            System.out.println("Value " + i + " : " + randomAccessFile.readDouble());
        }
        System.out.println(randomAccessFile.readUTF());
        randomAccessFile.close();
    }

    public static void main(String[] args) throws IOException {
        // rw: 读写
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        // 写入三个数字和一个字符串来标示已经到结尾了
        for (int i = 0; i < 3; i++) {
            randomAccessFile.writeDouble(i * 2.33);
        }
        randomAccessFile.writeUTF("The end of the file");
        randomAccessFile.close();

        // 读出
        display();

        randomAccessFile = new RandomAccessFile(file, "rw");
        // 因为double总是 8 字节长，所以查找第 2 个双精度值需要用 5 * 8
        randomAccessFile.seek(2 * 8);
        // 在当前文件指针处写入数据
        randomAccessFile.writeDouble(47.0001);
        randomAccessFile.close();
        display();
    }
}
