package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {

    private static final int B_SIZE = 1024;
    private static String file = ConstUtils.OUT_PREFIX + "data2.txt";

    public static void main(String[] args) throws Exception {
        // 通过 FileOutputStream 打开通道，写入数据
        FileChannel fileChannel = new FileOutputStream(file).getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text".getBytes()));
        fileChannel.close();

        // 通过 FileInputStream 打开通道，
        fileChannel = new FileInputStream(file).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(B_SIZE);
        fileChannel.read(buffer);
        buffer.flip();

        // 并不能工作，输出乱码
        System.out.println(buffer.asCharBuffer());

        // 使用系统默认的字符集来编码
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using " + encoding + ": " + Charset.forName(encoding).decode(buffer));

        // 用 UTF-16BE 格式来写入数据
        fileChannel = new FileOutputStream(file).getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fileChannel.close();

        // 编码后，现在再来读取数据
        readAndDisplay(buffer);

        // 使用 CharBuffer 来写入
        fileChannel = new FileOutputStream(file).getChannel();
        // 分配 24 个字节，一个字符需要 2 个字节
        buffer = ByteBuffer.allocate(24);
        buffer.asCharBuffer().put("Some text");
        fileChannel.write(buffer);
        fileChannel.close();

        readAndDisplay(buffer);


    }

    private static void readAndDisplay(ByteBuffer buffer) throws Exception {
        FileChannel fileChannel = new FileInputStream(file).getChannel();
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        // 通过 asCharBuffer() 获取缓冲器中的所有字符
        System.out.println(buffer.asCharBuffer());
    }

}
