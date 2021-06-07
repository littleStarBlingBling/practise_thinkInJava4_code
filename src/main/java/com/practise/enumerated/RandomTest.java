package com.practise.enumerated;

import com.practise.utils.EnumUtils;

enum Activity{
    SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FALLING, FLYING
}

public class RandomTest {
    public static void main(String[] args) {
        for(int i = 0; i< 6; i++){
            System.out.print(EnumUtils.random(Activity.class) + " ");
        }
    }
}
