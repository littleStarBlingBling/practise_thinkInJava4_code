package com.practise.enumerated.menu;

import com.practise.utils.EnumUtils;

// 枚举 - 接口 - 枚举
public enum  Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;

    private Course(Class<? extends Food> kind){
        // 通过 class 对象来拿到一个 Food 子类中所有的 enum 实例
        values = kind.getEnumConstants();
    }

    public Food randomSelection(){
        return EnumUtils.random(values);
    }

}
