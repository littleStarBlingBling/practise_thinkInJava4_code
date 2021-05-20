package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class MappedIO {

    private static int numOfInts = 4000000;

    // 读写很耗性能，所以用了更小的值
    private static int numOfUbuffInts = 200000;
    private static final String FILE_NAME = ConstUtils.OUT_PREFIX + "temp.tmp";

    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            System.out.println(name + ": ");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.2f\n", duration / 1.0e9);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public abstract void test() throws IOException;
    }

    // 匿名内部类数组
    private static Tester[] tests = {
            new Tester("Stream Write") {
                @Override
                public void test() throws IOException {
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(FILE_NAME))));

                    for (int i = 0; i < numOfInts; i++) {
                        out.writeInt(i);
                    }
                    out.close();
                }
            },

            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fileChannel = new RandomAccessFile(FILE_NAME, "rw").getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size()).asIntBuffer();

                    for (int i = 0; i < numOfInts; i++) {
                        intBuffer.put(i);
                    }
                    fileChannel.close();
                }
            },

            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)));
                    for (int i = 0; i < numOfInts; i++) {
                        in.readInt();
                    }

                    in.close();
                }
            },

            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fileChannel = new FileInputStream(new File(FILE_NAME)).getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size()).asIntBuffer();
                    while (intBuffer.hasRemaining()) {
                        intBuffer.get();
                    }

                    fileChannel.close();
                }
            },

            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(new File(FILE_NAME), "rw");
                    randomAccessFile.writeInt(1);

                    for (int i = 0; i < numOfUbuffInts; i++) {
                        randomAccessFile.seek(randomAccessFile.length() - 4);
                        randomAccessFile.writeInt(randomAccessFile.readInt());
                    }

                    randomAccessFile.close();
                }
            },

            new Tester("Mapped Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fileChannel = new RandomAccessFile(new File(FILE_NAME), "rw").getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size()).asIntBuffer();
                    intBuffer.put(0);

                    for (int i = 1; i < numOfUbuffInts; i++) {
                        intBuffer.put(intBuffer.get(i - 1));
                    }
                    fileChannel.close();
                }
            }

    };

    public static void main(String[] args) {
        for (Tester tester : tests) {
            tester.runTest();
        }
    }
}
