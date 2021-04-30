package com.practise.typeinfo;

interface HasBatteries { }

interface WaterProof { }

interface Shoots { }

class Toy {
    Toy() { }

    Toy(int i) { }
}

class FancyToy extends Toy implements HasBatteries, WaterProof, Shoots {
    FancyToy() {
        super(1);
    }
}


public class ToyTest {

    static void printInfo(Class cc) {
        // 判断是不是接口
        System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
        // 获得不含包名的类名
        System.out.println("Simple name: " + cc.getSimpleName());
        // 获得全限定名
        System.out.println("Canonical name: " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            // 必须使用全限定名
            c = Class.forName("com.practise.typeinfo.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }

        printInfo(c);

        // 遍历对象上的接口
        for (Class face : c.getInterfaces()) {
            printInfo(face);
        }

        // 获取到父类
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            // 使用newInstance()来创建的类，必须带有默认构造器
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Can't instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Can't access");
            System.exit(1);
        }

        printInfo(obj.getClass());
    }
}
