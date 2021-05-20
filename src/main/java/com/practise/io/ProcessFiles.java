package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.File;
import java.io.IOException;

public class ProcessFiles {

    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;

    // 文件后缀
    private String suffix;

    public ProcessFiles(Strategy strategy, String suffix) {
        this.strategy = strategy;
        this.suffix = suffix;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0) {
                processDirectoryTree(new File(ConstUtils.DOCUMENT_PREFIX));
            } else {
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory()) {
                        processDirectoryTree(fileArg);
                    } else {
                        if (!arg.endsWith("." + suffix)) {
                            arg += "." + suffix;
                        }
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(root.getAbsolutePath(), ".*\\." + suffix)) {
            strategy.process(file.getCanonicalFile());
        }
    }

    public static void main(String[] args) {
        new ProcessFiles(new ProcessFiles.Strategy() {
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        }, "txt").start(args);
    }
}
