package com.practise.containers;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SpringDetector {

    public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception {
        Constructor<T> groundhog = type.getConstructor(int.class);
        // 创建四个土拔鼠与预测的关联数据
        Map<Groundhog, Prediction> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            map.put(groundhog.newInstance(i), new Prediction());
        }
        System.out.println("map = " + map);

        // 创建一个 map 中数据有相同的编号的土拔鼠
        Groundhog groundhog2 = groundhog.newInstance(3);
        System.out.println("Looking up prediction for " + groundhog2);

        // 正确编写了 equals 和 hashCode 方法的土拔鼠才能被正常取出
        if (map.containsKey(groundhog2)) {
            System.out.println(map.get(groundhog2));
        } else {
            System.out.println("Key not found: " + groundhog2);
        }
    }

    public static void main(String[] args) throws Exception {
        detectSpring(Groundhog.class);
    }
}
