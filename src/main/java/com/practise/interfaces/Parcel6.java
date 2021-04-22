package com.practise.interfaces;

public class Parcel6 {

    public Wrapping wrapping(int x){

        return new Wrapping(x){
            @Override
            public int value(){
                return super.value() * 47;
            }
        };
    }

    public static void main(String[] args) {
        Parcel6 parcel6 = new Parcel6();
        Wrapping wrapping = parcel6.wrapping(2);
        System.out.println(wrapping.value());
    }
}
