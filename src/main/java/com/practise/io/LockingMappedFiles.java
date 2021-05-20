package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class LockingMappedFiles {

    // 128MB
    private static final int LENGTH = 0x8FFFFFF;
    private static FileChannel fileChannel;

    public static void main(String[] args) throws Exception {
        fileChannel = new RandomAccessFile(ConstUtils.OUT_PREFIX + "testLockMapped.dat", "rw").getChannel();
        MappedByteBuffer out = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            out.put((byte) 'x');
        }

        new LockAndModify(out, 0, LENGTH / 3);
        new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
    }

    // 创建可执行的任务
    private static class LockAndModify extends Thread {
        private ByteBuffer buffer;
        private int start, end;

        public LockAndModify(ByteBuffer buffer, int start, int end) {
            this.start = start;
            this.end = end;
            buffer.limit(end);
            buffer.position(start);
            // 创建这个 buffer 内容的共享子序列的新 ByteBuffer
            this.buffer = buffer.slice();
            // 启动线程
            start();
        }

        @Override
        public void run() {
            try {
                // 互斥锁，没有重叠
                FileLock fileLock = fileChannel.lock(start, end, false);
                System.out.println("Locked: " + start + " to " + end);

                // 执行修改
                while (buffer.position() < buffer.limit() - 1) {
                    buffer.put((byte) (buffer.get() + 1));
                }
                fileLock.release();
                System.out.println("Released: " + start + " to " + end);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
