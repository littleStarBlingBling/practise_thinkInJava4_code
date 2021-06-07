package com.practise.enumerated.menu;


public class Meal {

    public static void main(String[] args) {
        // 每分菜单中都包含 Course 的四个枚举常量
        for (int i = 0; i < 3; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.print(food + " ");
            }

            System.out.println();
        }
    }
}
