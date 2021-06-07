package com.practise.enumerated;

import java.util.EnumSet;
import static com.practise.enumerated.AlarmPoints.*;

public class EnumSets {

    public static void main(String[] args) {
        // 用指定的枚举类型来创建一个空的 EnumSet
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
        points.add(AlarmPoints.BATHROOM);
        System.out.println(points);

        points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        System.out.println(points);

        // 创建一个包含指定枚举类型中所有枚举常量的 EnumSet
        points = EnumSet.allOf(AlarmPoints.class);
        System.out.println(points);

        // 移除这三个枚举常量
        points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        System.out.println(points);
        // 移除这两个枚举常量范围内的值
        points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
        System.out.println(points);

        // 创建一个与指定 EnumSet 相同枚举类型，但枚举常量不同的 EnumSet，也就是补集
        points = EnumSet.complementOf(points);
        System.out.println(points);
    }
}
