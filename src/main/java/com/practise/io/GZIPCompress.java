package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPCompress {

    private static String file = ConstUtils.OUT_PREFIX + "testGZIP.gz";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(ConstUtils.IO_PREFIX + "GZIPCompress.java"));
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
        System.out.println("Writing file");
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }

        in.close();
        out.close();
        System.out.println("Reading file");
        // 读取压缩文件中的内容，并打印到控制台
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))));
        String s;
        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }
    }
}
