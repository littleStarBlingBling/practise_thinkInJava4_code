package com.practise.io;

import java.nio.ByteBuffer;

public class GetData {

    private static final int B_SIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(B_SIZE);

        // 检查 ByteBuffer 并自动分配内容为 0
        int i = 0;
        while (i ++ < buffer.limit()){
            if(buffer.get() != 0){
                System.out.println("nonzero");
            }
        }

        System.out.println("i = " + i);
        buffer.rewind();

        // 存储并读取一个 char 数组
        buffer.asCharBuffer().put("Howdy!");
        char c;
        while((c = buffer.getChar()) != 0){
            System.out.print(c + " ");
        }
        System.out.println();
        buffer.rewind();

        // 存储并读取一个 short
        buffer.asShortBuffer().put((short)12345);
        System.out.println(buffer.getShort());
        buffer.rewind();

        // 存储并读取一个 int
        buffer.asIntBuffer().put(12345678);
        System.out.println(buffer.getInt());
        buffer.rewind();

        // 存储并读取一个 long
        buffer.asLongBuffer().put(12345678);
        System.out.println(buffer.getLong());
        buffer.rewind();

        // 存储并读取一个 float
        buffer.asFloatBuffer().put(12345678);
        System.out.println(buffer.getFloat());
        buffer.rewind();

        // 存储并读取一个 double
        buffer.asDoubleBuffer().put(12345678);
        System.out.println(buffer.getDouble());
        buffer.rewind();

    }
}
