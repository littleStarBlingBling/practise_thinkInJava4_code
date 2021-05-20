package com.practise.io;

import java.util.Arrays;
import java.util.Collection;

public class PPrint {

    // 格式化集合中的数据
    public static String pformat(Collection<?> collection) {
        if (collection.size() == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        for (Object item : collection) {
            if (collection.size() != 1) {
                result.append("\n");
            }

            result.append(item);
        }

        // 在集合前后都加上换行符
        if(collection.size() != 1){
            result.append("\n");
        }
        return result.toString();
    }

    public static void pprint(Collection<?> collection){
        System.out.println(pformat(collection));
    }

    public static void pprint(Object[] objects){
        System.out.println(pformat(Arrays.asList(objects)));
    }
}
