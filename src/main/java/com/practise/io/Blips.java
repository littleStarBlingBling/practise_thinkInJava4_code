package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;

class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {
    // 非 public 的构造器
    Blip2() {
        System.out.println("Blip2 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}

public class Blips {
    public static void main(String[] args) throws Exception {
        System.out.println("Constructing objects");
        Blip1 blip1 = new Blip1();
        Blip2 blip2 = new Blip2();

        // 序列化到文件中
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "Blips.out"));
        System.out.println("Saving objects");
        out.writeObject(blip1);
        out.writeObject(blip2);
        out.close();

        // 现在来反序列化，取回数据
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(ConstUtils.OUT_PREFIX + "Blips.out"));
        System.out.println("Recovering blip1: ");
        blip1 = (Blip1) in.readObject();

        // 因为Blip2的构造器不是public的，会发生异常：java.io.InvalidClassException: Blip2; no valid constructor
//		System.out.println("Recovering blip2: ");
//		blip2 = (Blip2) in.readObject();
    }
}
