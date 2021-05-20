package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {

    private static final int B_SIZE = 1024;

    public static void main(String[] args) throws Exception {
        FileChannel in = new FileInputStream(ConstUtils.IO_PREFIX + "ChannelCopy.java").getChannel(),
                out = new FileOutputStream(ConstUtils.OUT_PREFIX + "ChannelCopy.txt").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(B_SIZE);
        while (in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
    }
}
