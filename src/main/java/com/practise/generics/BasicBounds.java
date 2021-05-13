package com.practise.generics;

import java.awt.*;

interface HasColor {
    Color getColor();
}

// 泛型参数为 HasColor 的实现类
class Colored<T extends HasColor> {

    T item;

    Colored(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    // 可以调用 HasColor 中声明的方法
    Color color() {
        return item.getColor();
    }
}

// 作为边界的普通类
class Dimension {
    public int x, y, z;
}

// 编译出错，需要先类再接口
//class ColoredDimension<T extends HasColor & Dimension> { }

// 多重边界
class ColoredDimension<T extends Dimension & HasColor> {
    T item;

    ColoredDimension(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    Color color() {
        return item.getColor();
    }

    // 获取到 Dimension 中的属性
    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }
}

interface Weight {
    int weight();
}

// 与普通类继承一样，泛型参数只能有一个具体的类但是可以有多个接口
class Solid <T extends Dimension & HasColor & Weight> {

    T item;

    public Solid(T item) {
        this.item = item;
    }

    Color color() {
        return item.getColor();
    }
}

// 满足上述泛型参数要求的类
class Bounded extends Dimension implements HasColor, Weight {

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }
}

public class BasicBounds {
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<>(new Bounded());
        solid.color();
    }
}
