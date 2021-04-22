package com.practise.interfaces;

class Parcel2 {

    // 包裹的内容、目的地都是私有的内部类
    private class PContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected class PDestination implements Destination {
        private String label;

        private PDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    // 通过外围类来提供内部类引用
    public Destination destination(String s){
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }

}


public class TestParcel {
    public static void main(String[] args) {
        Parcel2 parcel2 = new Parcel2();
        Contents contents = parcel2.contents();
        Destination destination = parcel2.destination("China");

//		不能访问到私有的内部类
//		Parcel2.PDestination pd = parcel2.new PDestination();
//		Parcel2.PContents pc = parcel2.new PContents();
    }
}
