package com.practise.interfaces;

public class Parcel5 {
    // 在 return 语句中，创建了实现接口的匿名对象
    public Contents contents() {
        return new Contents() {
            private int i = 11;

            @Override
            public int value() {
                return i;
            }
        };
    }

    public static void main(String[] args) {
        Parcel5 parcel5 = new Parcel5();
        Contents contents = parcel5.contents();
        System.out.println(contents.value());
    }
}
