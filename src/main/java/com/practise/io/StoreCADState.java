package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Shape implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random random = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int color);

    public abstract int getColor();

    public Shape(int xPos, int yPos, int dimension) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return getClass() + "color[" + getColor() + "] xPos[" + xPos + "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }

    public static Shape randomFactory() {
        int xVal = random.nextInt(100);
        int yVal = random.nextInt(100);
        int dim = random.nextInt(100);
        switch (counter++ % 3) {
            default:
            case 0:
                return new Circle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);
        }
    }
}

//
class Circle extends Shape {
    private static int color = RED;

    public Circle(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int color) {
        Circle.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }
}

class Square extends Shape {

    private static int color;

    public Square(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
        color = BLUE;
    }

    @Override
    public void setColor(int color) {
        Square.color = color;

    }

    @Override
    public int getColor() {
        return color;
    }
}

// 有自己的序列化方法
class Line extends Shape {
    private static int color = RED;

    public Line(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int color) {
        Line.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    public static void serializeStaticState(ObjectOutputStream out) throws IOException {
        out.writeInt(color);
    }

    public static void deserializeStaticState(ObjectInputStream in) throws IOException {
        Line.color = in.readInt();
    }
}

public class StoreCADState {

    public static void main(String[] args) throws Exception {

        // 三种形状的 Class 对象列表
        List<Class<? extends Shape>> shapeTypes = new ArrayList<>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);

        // 生成一些形状
        List<Shape> shapeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shapeList.add(Shape.randomFactory());
        }

        // 给所有的形状修改颜色
        for (int i = 0; i < 10; i++) {
            shapeList.get(i).setColor(Shape.GREEN);
        }
        System.out.println(shapeList);

        // 序列化形状的类列表
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ConstUtils.OUT_PREFIX + "CADState.out"));
        out.writeObject(shapeTypes);
        // Line 单独执行自己的序列化
        Line.serializeStaticState(out);
        // 序列化形状列表
        out.writeObject(shapeList);
        out.close();

    }
}
