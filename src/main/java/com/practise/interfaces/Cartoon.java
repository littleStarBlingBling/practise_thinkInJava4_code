package com.practise.interfaces;

// 基类
class Art{
    Art(){
        System.out.println("Art constructor");
    }
}

// 导出类
class Drawing extends Art{
    Drawing(){
        System.out.println("Drawing constructor");
    }
}

// 导出类的导出类
public class Cartoon extends Drawing{
    public Cartoon(){
        System.out.println("Cartoon constructor");
    }
    public static void main(String[] args){
        Cartoon x = new Cartoon();
    }
}