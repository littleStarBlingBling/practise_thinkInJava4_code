package com.practise.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {

    public static void trackUseCases(List<Integer> useCases, Class<?> clazz) {
        // 遍历类上的方法，处理有 @UseCase 注解有方法
        for (Method method : clazz.getDeclaredMethods()) {
            UseCase useCase = method.getAnnotation(UseCase.class);
            if (useCase != null) {
                System.out.println("Found Use Case: " + useCase.id());
                // 移除存在的编号
                useCases.remove(new Integer(useCase.id()));
            }
        }

        for (int i : useCases) {
            System.out.println("Warning: Missing use case: " + i);
        }
    }

    public static void main(String[] args) {
        // 用例编号
        List<Integer> useCaseList = new ArrayList<>();
        Collections.addAll(useCaseList, 47, 48, 49, 50);
        trackUseCases(useCaseList, PasswordUtils.class);
    }

}
