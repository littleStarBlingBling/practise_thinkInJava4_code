package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList2 {

    // 方法中的匿名内部类，不用单独声明成一个类
    public static FilenameFilter filter(final String regex) {

        return new FilenameFilter() {

            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) {

        File path = new File(ConstUtils.DOCUMENT_PREFIX);

        String[] list = path.list(new DirFilter(ConstUtils.TXT_REGEX));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}
