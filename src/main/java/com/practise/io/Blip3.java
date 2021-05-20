package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;

public class Blip3 implements Externalizable {

    private int i;
    private String s;

    public Blip3() {
        System.out.println("Blip3 Constructor");
    }

    public Blip3(int i, String s) {
        System.out.println("Blip3(int i, String s)");
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString() {
        return s + i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        s = (String)in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Constructing objects: ");
        Blip3 blip3 = new Blip3(47, "A String ");
        System.out.println(blip3);

        // 序列化
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "Blip3.out"));
        System.out.println("Saving object: ");
        out.writeObject(blip3);
        out.close();

        // 反序列化
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(ConstUtils.OUT_PREFIX + "Blip3.out"));
        System.out.println("Recovering blip3: ");
        // 可以从输出看到，恢复对象时调用的是默认的无参构造器
        blip3 = (Blip3) in.readObject();
        System.out.println(blip3);
    }
}
