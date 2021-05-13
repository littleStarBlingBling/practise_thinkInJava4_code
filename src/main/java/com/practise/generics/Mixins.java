package com.practise.generics;

import java.util.Date;

// 定义了三个接口与对应的实现类，作为要被混型的类
interface TimeStamped {
    long getStamp();
}

class TimeStampedImpl implements TimeStamped {

    private final long timeStamp;

    public TimeStampedImpl() {
        this.timeStamp = new Date().getTime();
    }

    @Override
    public long getStamp() {
        return timeStamp;
    }
}

interface SerialNumbered {
    long getSerialNumber();
}

class SerialNumberedImpl implements SerialNumbered {
    private static long counter = 1;

    private final long serialNumber = counter++;

    @Override
    public long getSerialNumber() {
        return serialNumber;
    }
}

interface Basic {
    void set(String val);

    String get();
}

class BasicImpl implements Basic {
    private String value;

    @Override
    public void set(String val) {
        this.value = val;
    }

    @Override
    public String get() {
        return value;
    }
}

// 混型类继承了一个类实现了两个接口
class Mixin extends BasicImpl implements TimeStamped, SerialNumbered {

    // 混型类需要持有接口的实例
    private TimeStamped timeStamp = new TimeStampedImpl();

    private SerialNumbered serialNumber = new SerialNumberedImpl();

    // 转发调用，而不是自己实现
    @Override
    public long getStamp() {
        return timeStamp.getStamp();
    }

    @Override
    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }
}

public class Mixins {

    public static void main(String[] args) {
        Mixin mixin1 = new Mixin(),
                mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1.get() + " " + mixin1.getStamp() + " " + mixin1.getSerialNumber());
        System.out.println(mixin2.get() + " " + mixin2.getStamp() + " " + mixin2.getSerialNumber());
    }
}
