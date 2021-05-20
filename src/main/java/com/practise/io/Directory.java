package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directory {

    // 方法内部匿名类
    // listFiles() 方法返回的是 File() 对象数组，而 list() 方法返回的是文件/目录的 String 数组
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {

            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    // 收集文件列表与目录列表
    public static class TreeInfo implements Iterable<File> {
        public List<File> fileList = new ArrayList<>();
        public List<File> dirList = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return fileList.iterator();
        }

        void addAll(TreeInfo other) {
            fileList.addAll(other.fileList);
            dirList.addAll(other.dirList);
        }

        @Override
        public String toString() {
            return "dirs: \n" + PPrint.pformat(dirList) + "\n\nfiles: " + PPrint.pformat(fileList);
        }
    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(File start, String regex) {
        return recurseDirs(start, regex);
    }

    public static TreeInfo walk(File start) {
        return recurseDirs(start, ".*");
    }

    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }

    // 递归目录，分别将目录和文件放入列表中
    private static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                result.dirList.add(item);
                // 递归下级目录
                result.addAll(recurseDirs(item, regex));
            } else {
                if (item.getName().matches(regex)) {
                    result.fileList.add(item);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- 目录结构如下 ---\n");
        System.out.println(walk(ConstUtils.DOCUMENT_PREFIX));

        System.out.println("--- 使用local()方法进行过滤文件 ---");
        List<File> fileList = Arrays.asList(local(ConstUtils.DOCUMENT_PREFIX, ConstUtils.JPEG_REGEX));
        System.out.println(PPrint.pformat(fileList));
    }
}
