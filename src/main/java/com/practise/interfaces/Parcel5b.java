package com.practise.interfaces;

public class Parcel5b {

    // 先声明内部类，再在方法中返回内部类的实例
    class MyContents implements Contents{
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    public Contents contents() {
        return new MyContents();
    }

    public static void main(String[] args) {
        Parcel5b parcel5b = new Parcel5b();
        Contents contents = parcel5b.contents();
        System.out.println(contents.value());
    }
}
