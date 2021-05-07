package com.practise.arrays;

import com.practise.generics.Generator;

public class GeneratorsTest {

    public static int size = 6;

    public static void test(Class<?> surroundingClass) {

        // 遍历这个类中的所有 public 类和接口
        for (Class<?> type : surroundingClass.getClasses()) {
            System.out.print(type.getSimpleName() + "： ");
            try {
                // 拿到生成器实例，生成 6 个对应类型的数据
                Generator<?> generator = (Generator<?>) type.newInstance();
                for (int i = 0; i < size; i++) {
                    System.out.print(generator.next() + " ");
                }
                System.out.println();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args) {
        test(RandomGenerator.class);
    }
}
