package com.practise.generics;

// 导出类把导出类类型的引用传递给基类
class SubType extends BasicHolder<SubType>{ }

public class CRGWithBasicHolder {

    public static void main(String[] args) {
        SubType subType1 = new SubType(),
                subType2 = new SubType();
        subType1.set(subType2);

        SubType subType3 = subType1.get();
        subType1.f();
    }
}
