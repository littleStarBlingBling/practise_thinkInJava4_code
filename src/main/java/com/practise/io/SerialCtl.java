package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;

public class SerialCtl implements Serializable {
    private String a;
    // 不序列化 b 字段
    private transient String b;

    public SerialCtl(String a, String b) {
        this.a = "Not transient: " + a;
        this.b = "Transient: " + b;
    }

    @Override
    public String toString() {
        return a + "\n" + b;
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        // 依然可以执行默认的序列化机制
        outputStream.defaultWriteObject();
        // 自定义的部分，可以从输出中看到 transient 修饰的字段依然被序列化了
        outputStream.writeObject(b);
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        b = (String) inputStream.readObject();
    }

    public static void main(String[] bu) throws Exception {
        SerialCtl serialCtl = new SerialCtl("Test1", "Test2");
        System.out.println("Before:\n" + serialCtl);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "transient.out"));
        out.writeObject(serialCtl);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(ConstUtils.OUT_PREFIX + "transient.out"));
        SerialCtl result = (SerialCtl) in.readObject();
        System.out.println("After:\n" + result);
    }
}
