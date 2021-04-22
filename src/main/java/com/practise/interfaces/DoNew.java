package com.practise.interfaces;

public class DoNew {
    public class Inner{}

    public static void main(String[] args) {
        DoNew doNew = new DoNew();
        // 通过 new 创建内部类实例
        DoNew.Inner dni = doNew.new Inner();
    }
}