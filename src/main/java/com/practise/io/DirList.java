package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {

    public static void main(String[] args) {

        File path = new File(ConstUtils.DOCUMENT_PREFIX);

        // 抽像路径下所有的文件和目录，并且符合过滤条件的字符串名称数组
        String[] list = path.list(new DirFilter(ConstUtils.TXT_REGEX));
        // 大小写不敏感的排序
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}

// 自定义的文件名过滤器
class DirFilter implements FilenameFilter {

    private Pattern pattern;

    public DirFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
