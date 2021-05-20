package com.practise.enumerated.menu;

import static com.practise.enumerated.menu.Food.*;

public class TypeOfFood {

    public static void main(String[] args) {
        Food food = Appetizer.SALAD;
        food = MainCourse.LASAGNE;
        food = Dessert.GELATO;
        food = Coffee.CAPPUCCINO;
    }
}
