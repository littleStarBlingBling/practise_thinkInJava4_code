package com.practise.generics;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Apply {

    // S 是存储 T 对象，并且实现了 Iterable 接口的可以遍历的集合
    public static <T, S extends Iterable<? extends T>> void apply(S seq, Method f, Object... args) {
        try {
            for (T t : seq) {
                f.invoke(t, args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Shape {
    public void rotate() {
        System.out.println(this + " rotate");
    }

    public void resize(int newSize) {
        System.out.println(this + " resize " + newSize);
    }
}

class Square extends Shape { }

// 自定义的列表类，通过反射来生成新对象
class FilledList<T> extends ArrayList<T> {

    public FilledList(Class<? extends T> type, int size) {
        try {
            for (int i = 0; i < size; i++) {
                add(type.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ApplyTest {

    public static void main(String[] args) throws Exception {
        List<Shape> shapeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            shapeList.add(new Shape());
        }

        List<Square> squareList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            squareList.add(new Square());
        }

        SimpleQueue<Shape> shapeSimpleQueue = new SimpleQueue<>();
        for (int i = 0; i < 3; i++) {
            shapeSimpleQueue.add(new Shape());
            shapeSimpleQueue.add(new Square());
        }

        // apply() 方法用于不同对象的集合
        Apply.apply(shapeList, Shape.class.getMethod("rotate"));
        Apply.apply(shapeList, Shape.class.getMethod("resize", int.class), 3);

        Apply.apply(squareList, Shape.class.getMethod("rotate"));
        Apply.apply(squareList, Shape.class.getMethod("resize", int.class), 3);

        Apply.apply(new FilledList<>(Shape.class, 3), Shape.class.getMethod("rotate"));

        Apply.apply(shapeSimpleQueue, Shape.class.getMethod("rotate"));
    }
}
