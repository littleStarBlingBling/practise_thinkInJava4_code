package com.practise.typeinfo;

class Building{}

class House extends Building{}

public class ClassCasts {
    public static void main(String[] args) {
        // 子类向上转型
        Building building = new House();
        // 子类的 Class 对象
        Class<House> houseType = House.class;
        // 通过子类的 Class 对象的 cast() 方法把父类转换成子类对象
        House house = houseType.cast(building);
        // 父类向下转型
        house = (House)building;
    }
}