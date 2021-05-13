package com.practise.generics;

import java.util.Date;

class Basic2 {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

// 装饰 Basic2
class Decorator extends Basic2 {

    // 持有被装饰物的类型，并把对应方法调用转发给被装饰物
    protected Basic2 basic2;

    public Decorator(Basic2 basic2) {
        this.basic2 = basic2;
    }

    public void set(String val) {
        basic2.setValue(val);
    }

    public String get() {
        return basic2.getValue();
    }
}

class TimeStamped2 extends Decorator {

    private final long timeStamp;

    // 在构造器中传入被装饰物的实例
    public TimeStamped2(Basic2 basic2) {
        super(basic2);
        this.timeStamp = new Date().getTime();
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}

class SerialNumbered2 extends Decorator {

    private static long counter = 1;
    private final long serialNumber = counter++;

    public SerialNumbered2(Basic2 basic2) {
        super(basic2);
    }

    public long getSerialNumber() {
        return serialNumber;
    }
}

public class Decoration {
    // 在这个继承体系中，TimeStamped2 与 SerialNumbered2 实例无法访问到定义在对方类中的方法
    public static void main(String[] args) {
        TimeStamped2 t1 = new TimeStamped2(new Basic2());
        TimeStamped2 t2 = new TimeStamped2(new SerialNumbered2(new Basic2()));
//		编译出错，Not available
//		t2.getSerialNumber();

        SerialNumbered2 s1 = new SerialNumbered2(new Basic2());
        SerialNumbered2 s2 = new SerialNumbered2(new TimeStamped2(new Basic2()));

//		编译出错，Not available
//		s2.getTimeStamp();

    }
}
