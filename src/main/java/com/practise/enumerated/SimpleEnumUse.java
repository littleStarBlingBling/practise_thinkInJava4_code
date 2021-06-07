package com.practise.enumerated;

public class SimpleEnumUse {

    public static void main(String[] args) {
        Spiciness spiciness = Spiciness.MEDIUM;
        System.out.println(spiciness);

        for(Spiciness s : Spiciness.values()){
            System.out.println(s + ", ordinal " + s.ordinal());
        }
    }
}
