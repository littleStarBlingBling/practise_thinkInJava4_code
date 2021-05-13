package com.practise.generics;

import java.util.ArrayList;
import java.util.List;

public class TestList2 {

    public static void main(String[] args) {
        List<? super Fruit> fruitList = new ArrayList<>();
        fruitList.add(new Fruit());
        fruitList.add(new Peach());
//		编译出错，add (capture<? super Fruit>) in List cannot be applied to Food
//		fruitList.add(new Food());
    }
}
