package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

public class ZipCompress {

    private static String testZip = ConstUtils.OUT_PREFIX + "testZip.zip";

    public static void main(String[] args) throws IOException {
        // 层层包装出 BufferedOutputStream
        FileOutputStream fileOut = new FileOutputStream(testZip);
        // 指定输出的校验和格式
        CheckedOutputStream checkOut = new CheckedOutputStream(fileOut, new Adler32());
        ZipOutputStream zipOut = new ZipOutputStream(checkOut);
        BufferedOutputStream out = new BufferedOutputStream(zipOut);

        String file = ConstUtils.IO_PREFIX + "ZipCompress.java";
        System.out.println("Writing file " + file);
        // 用到了字符流
        BufferedReader in = new BufferedReader(new FileReader(file));
        // 把文件加入到 ZIP 压缩包中
        zipOut.putNextEntry(new ZipEntry(file));

        // 将读取到的内容写入 BufferedOutputStream 中
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        in.close();
        out.close();

        System.out.println("Checksum: " + checkOut.getChecksum().getValue());

        // 解压缩文件
        System.out.println("Reading file");
        FileInputStream fileIn = new FileInputStream(testZip);
        CheckedInputStream checkIn = new CheckedInputStream(fileIn, new Adler32());
        ZipInputStream zipIn = new ZipInputStream(checkIn);
        BufferedInputStream bufferIn = new BufferedInputStream(zipIn);

        ZipEntry zipEntry;
        while ((zipEntry = zipIn.getNextEntry()) != null) {
            System.out.println("Reading file " + zipEntry);
            int x;
            while ((x = bufferIn.read()) != -1) {
                System.out.write(x);
            }
        }

        bufferIn.close();

        // 解压缩文件更方便的方法，直接遍历枚举
        ZipFile zipFile = new ZipFile(testZip);
        Enumeration enumeration = zipFile.entries();
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry2 = (ZipEntry) enumeration.nextElement();
            System.out.println("File: " + zipEntry2);
        }

    }
}
