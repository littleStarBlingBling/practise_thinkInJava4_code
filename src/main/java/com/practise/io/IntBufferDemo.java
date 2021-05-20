package com.practise.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
    private static final int B_SIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(B_SIZE);
        IntBuffer intBuffer = buffer.asIntBuffer();

        // 存储一个int数组
        intBuffer.put(new int[]{2, 4, 6, 8, 10});

        // 绝对地址的读写
        System.out.println(intBuffer.get(3));
        intBuffer.put(3, 1024);

        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            int i = intBuffer.get();
            System.out.println(i);
        }
    }
}
