package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMappedFiles {
    // 128 MB
    private static int length = 0x8FFFFFF;

    public static void main(String[] args) throws Exception {

        MappedByteBuffer out = new RandomAccessFile(ConstUtils.OUT_PREFIX + "testLarge.dat", "rw")
                .getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }

        System.out.println("Finished writing");

        // 只输出部分内容
        for (int i = length / 2; i < length / 2 + 6; i++) {
            System.out.print((char) out.get(i));
        }

    }
}
