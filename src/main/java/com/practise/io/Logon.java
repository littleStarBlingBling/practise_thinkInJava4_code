package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    // 不序列化 password 字段
    private transient String password;

    public Logon(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "logon info: \n username: " + username + "\n date: " + date + "\n password: " + password;
    }

    public static void main(String[] args) throws Exception{
        Logon logon = new Logon("MyDear", "littleStarBlingBling");
        System.out.println("logon  = " + logon);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "Logon.out"));
        out.writeObject(logon);
        out.close();

        TimeUnit.SECONDS.sleep(1);
        // 反序列化后看看 password
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(ConstUtils.OUT_PREFIX + "Logon.out"));
        System.out.println("Recovering object at " + new Date());
        logon = (Logon)in.readObject();
        System.out.println("logon = " + logon);

    }
}
