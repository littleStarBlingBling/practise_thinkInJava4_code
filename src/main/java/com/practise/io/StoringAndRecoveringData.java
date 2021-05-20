package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;

public class StoringAndRecoveringData {

    private static String file = ConstUtils.OUT_PREFIX + "Data.txt";

    public static void main(String[] args) throws IOException {
        // 先写入
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        out.writeDouble(3.14159);
        out.writeUTF("This apple is red");
        out.writeDouble(233.33);
        out.writeUTF("This puppy is cute");
        out.close();

        // 再读取数据
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
}
