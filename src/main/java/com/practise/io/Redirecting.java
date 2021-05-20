package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;

public class Redirecting {

    public static void main(String[] args) throws IOException {

        // 读取文件
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(ConstUtils.IO_PREFIX + "Redirecting.java"));
        // 输出到指定文件
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "testOut.log")));
        PrintStream err = new PrintStream(new BufferedOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "testErr.log")));

        // 分别重定向
        System.setIn(in);
        System.setOut(out);
        System.setErr(err);

        // 从 System.in 中获取数据流并写入
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 输出到已经重定向的 System.out 中
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }

        out.close();

    }
}
