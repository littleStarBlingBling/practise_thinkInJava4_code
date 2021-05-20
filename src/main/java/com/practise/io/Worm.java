package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;
import java.util.Random;

class Data implements Serializable {
    private int id;

    public Data(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}

public class Worm implements Serializable {
    // 随机生成 3 个数字
    private static Random random = new Random(47);
    private Data[] dataArray = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };

    // 指向下一个 Worm 对象
    private Worm next;
    private char c;

    // 递归生成链接的 Worm 列表
    public Worm(int i, char x) {
        System.out.println("Worm constructor: " + i);
        c = x;
        if (--i > 0) {
            next = new Worm(i, (char) (x + 1));
        }
    }

    // 反序列化没有调用任何构造器
    public Worm() {
        System.out.println("Default constructor");
    }

    // 打印出 Data 数组与下一个 Worm 对象的引用
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data data : dataArray) {
            result.append(data);
        }

        result.append(")");
        if (next != null) {
            result.append(next);
        }

        return result.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Worm worm = new Worm(6, 'a');
        System.out.println("w = " + worm);

        // 序列化到文件 worm.out
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "worm.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(worm);
        out.close();

        // 反序列化
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(ConstUtils.OUT_PREFIX + "worm.out"));
        // 读取出来的字符串是 "Worm storage\n"
        String s = (String) in.readObject();
        Worm w2 = (Worm) in.readObject();
        System.out.println(s + "w2 = " + w2);

        // 把反序列化后的 worm 对象写入字节流中
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(byteOut);
        out2.writeObject("Worm storage\n");
        out2.writeObject(worm);
        out2.flush();

        // 从字节流中读取数据
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
        s = (String) in2.readObject();
        Worm w3 = (Worm) in2.readObject();
        System.out.println(s + "w3 = " + w3);
    }
}
