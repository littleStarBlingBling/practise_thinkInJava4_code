package com.practise.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute {

    public static void command(String command) {
        boolean err = false;
        try {
            // 执行命令并获取到数据流
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // 输出到控制台
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }

            // 输出异常信息
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            while ((s = errors.readLine()) != null) {
                System.err.println(s);
                err = true;
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

        if (err) {
            throw new OSExecuteException("Errors executing " + command);
        }
    }
}
