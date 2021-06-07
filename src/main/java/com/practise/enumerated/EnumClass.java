package com.practise.enumerated;

// 灌木
enum Shrubbery{
    GROUND, CRAWLING, HANGING,;
}

public class EnumClass {
    public static void main(String[] args) {
        for(Shrubbery shrubbery : Shrubbery.values()){
            // 显示枚举常量声明的顺序
            System.out.println(shrubbery + " ordinal: " + shrubbery.ordinal());
            System.out.print(shrubbery.compareTo(Shrubbery.CRAWLING) + " ");
            System.out.print(shrubbery.equals(Shrubbery.CRAWLING) + " ");
            System.out.println(shrubbery == Shrubbery.CRAWLING);
            // 返回与此枚举常量对应的枚举类型Class对象
            System.out.println(shrubbery.getDeclaringClass());
            System.out.println(shrubbery.name());
            System.out.println("--------------------------------");
        }

        for(String s : "HANGING CRAWLING GROUND".split(" ")){
            // 返回指定枚举类型中指定名字的的枚举常量
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, s);
            System.out.println(shrubbery);
        }
    }
}
