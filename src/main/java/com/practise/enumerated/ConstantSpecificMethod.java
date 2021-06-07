package com.practise.enumerated;

import java.text.DateFormat;
import java.util.Date;

public enum  ConstantSpecificMethod {
    // 枚举常量，实现枚举类型中定义的抽象方法
    DATE_TIME{
        @Override
        String getInfo(){
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    VERSION{
        @Override
        String getInfo(){
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();

    public static void main(String[] args) {
        for(ConstantSpecificMethod method : values()){
            System.out.println(method.getInfo());
        }
    }
}
