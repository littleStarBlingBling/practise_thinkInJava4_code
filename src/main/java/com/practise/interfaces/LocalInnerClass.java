package com.practise.interfaces;

interface Counter {
    int next();
}

public class LocalInnerClass {

    private int count = 0;

    // 方法体中的内部类
    Counter getCounter(final String name) {
        class LocalCounter implements Counter {
            public LocalCounter() {
                System.out.println("LocalCounter()");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        }

        return new LocalCounter();
    }

    // 方法体中的匿名内部类
    Counter getCounter2(final String name) {
        return new Counter() {

            {
                System.out.println("Counter()");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        };
    }

    public static void main(String[] args) {
        LocalInnerClass localInnerClass = new LocalInnerClass();
        Counter
                c1 = localInnerClass.getCounter("Local Inner"),
                c2 = localInnerClass.getCounter2("Anonymous inner");

        for (int i = 0; i < 3; i++) {
            System.out.println(c1.next());
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(c2.next());
        }
    }

}
