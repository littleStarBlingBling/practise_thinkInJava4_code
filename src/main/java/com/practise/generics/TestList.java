package com.practise.generics;

import java.util.ArrayList;
import java.util.List;

public class TestList {

    public static void main(String[] args) {
        // 编译错误，不兼容类型
//        List<Food> foodList = new ArrayList<Fruit>();
        List<? extends Food> foodList = new ArrayList<Fruit>();

        // 编译出错，add (capture<? extends Food>) in List cannot be applied to ..
        // 现在任何对象都添加不进去了
//        foodList.add(new Food());
//        foodList.add(new Fruit());
//        foodList.add(new Meat());
    }
}
