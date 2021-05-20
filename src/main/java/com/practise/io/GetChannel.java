package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {

    private static final int B_SIZE = 1024;
    private static String file = ConstUtils.OUT_PREFIX + "data.txt";

    public static void main(String[] args) throws Exception {
        // 写一个文件
        FileChannel fileChannel = new FileOutputStream(file).getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text ".getBytes()));
        fileChannel.close();

        // 把数据添加到文件末尾
        fileChannel = new RandomAccessFile(file, "rw").getChannel();
        fileChannel.position(fileChannel.size());
        fileChannel.write(ByteBuffer.wrap("Some more".getBytes()));
        fileChannel.close();

        // 读取文件
        fileChannel = new FileInputStream(file).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(B_SIZE);
        fileChannel.read(buff);
        buff.flip();

        // 输出到控制台
        StringBuilder stringBuilder = new StringBuilder();
        while (buff.hasRemaining()) {
            stringBuilder.append((char) buff.get());
        }

        System.out.println(stringBuilder.toString());
    }
}
