package com.practise.interfaces;

public class Parcel7 {

    public Destination destination(final String dest){
        return new Destination() {
            private String label = dest;
            @Override
            public String readLabel(){
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel7 parcel7 = new Parcel7();
        Destination destination = parcel7.destination("China");
        System.out.println(destination.readLabel());
    }
}
