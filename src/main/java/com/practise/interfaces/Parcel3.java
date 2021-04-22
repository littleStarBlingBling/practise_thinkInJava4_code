package com.practise.interfaces;

public class Parcel3 {

    // 在方法中创建内部类
    public Destination destination(String s){

        class PDestination implements Destination{
            private String label;

            private PDestination(String whereTo){
                label = whereTo;
            }

            @Override
            public String readLabel(){
                return label;
            }
        }

        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel3 parcel3 = new Parcel3();
        Destination destination = parcel3.destination("China");
    }
}
