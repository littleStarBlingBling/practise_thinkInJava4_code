package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLocking {

    public static void main(String[] args)throws Exception {

        FileOutputStream out = new FileOutputStream(ConstUtils.OUT_PREFIX + "testLock.txt");
        FileLock fileLock = out.getChannel().tryLock();
        if(fileLock != null){
            System.out.println("Locked file");
            TimeUnit.MILLISECONDS.sleep(100);
            // 释放锁
            fileLock.release();
            System.out.println("Released lock");
        }

        out.close();
    }
}
