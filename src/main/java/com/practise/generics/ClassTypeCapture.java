package com.practise.generics;

class Building{}

class House extends Building{}

public class ClassTypeCapture<T> {

    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean judgeType(Object arg){
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> building = new ClassTypeCapture<>(Building.class);
        System.out.println(building.judgeType(new Building()));
        System.out.println(building.judgeType(new House()));

        ClassTypeCapture<House> house = new ClassTypeCapture<>(House.class);
        System.out.println(house.judgeType(new Building()));
        System.out.println(house.judgeType(new House()));
    }
}
