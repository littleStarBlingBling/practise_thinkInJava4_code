package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList3 {

    public static void main(String[] args) {
        File path = new File(ConstUtils.DOCUMENT_PREFIX);

        // 连单独的方法都不用，直接在传参的地方声明匿名内部类
        String[] list = path.list(new FilenameFilter() {

            private Pattern pattern = Pattern.compile(ConstUtils.TXT_REGEX);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}
