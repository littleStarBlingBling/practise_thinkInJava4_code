package com.practise.typeinfo;

// 静态的代码块会在类加载时执行
class Candy{
    static{
        System.out.println("Loading Candy");
    }
}

class Gum{
    static{
        System.out.println("Loading Gum");
    }
}

class Cookie{
    static{
        System.out.println("Loading Cookie");
    }
}

public class SweetShop {

    public static void main(String[] args) {
        // 使用 new 操作符触发类加载
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");

        // 使用 Class 的方法加载类
        try{
            Class.forName("com.practise.typeinfo.Gum");
        }catch (ClassNotFoundException e){
            System.out.println("Couldn't find Gum");
        }

        System.out.println("After Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");
    }
}
