package com.practise.io;

import com.practise.utils.ConstUtils;
import java.io.*;

public class FreezeAlien {

    public static void main(String[] args) throws Exception{
        // 序列化，将 Alien 对象写入到 Alien.txt 中
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "Alien.txt"));
        Alien alien = new Alien(1);
        out.writeObject(alien);
        out.close();

        // 反序列化，从 Alien.txt 中读出数据
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(ConstUtils.OUT_PREFIX + "Alien.txt"));
        Alien other = (Alien) input.readObject();
        System.out.println(other);
    }

}