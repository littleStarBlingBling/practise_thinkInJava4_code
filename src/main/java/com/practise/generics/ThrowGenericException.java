package com.practise.generics;

import java.util.ArrayList;
import java.util.List;

// process() 方法会抛出泛型异常类
interface Processor<T, E extends Exception> {
    void process(List<T> resultCollector) throws E;
}

// 保存 Processor 类实例并执行 process() 方法
class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T, E>> {

    List<T> processAll() throws E {

        List<T> resultCollector = new ArrayList<>();
        for (Processor<T, E> processor : this) {
            processor.process(resultCollector);
        }
        return resultCollector;
    }
}

// Process 实现类会抛出的第一种异常
class Failure1 extends Exception { }

class Processor1 implements Processor<String, Failure1> {

    static int count = 2;

    @Override
    public void process(List<String> resultCollector) throws Failure1 {
        if (count-- > 1) {
            resultCollector.add("Help!");
        } else {
            resultCollector.add("SOS!");
        }

        if (count < 0) {
            throw new Failure1();
        }
    }
}

// Process 实现类会抛出的第二种异常
class Failure2 extends Exception { }

class Processor2 implements Processor<Integer, Failure2> {
    static int count = 2;

    @Override
    public void process(List<Integer> resultCollector) throws Failure2 {
        if (count-- == 0) {
            resultCollector.add(2);
        } else {
            resultCollector.add(4);
        }

        if (count < 0) {
            throw new Failure2();
        }

    }
}

public class ThrowGenericException {

    public static void main(String[] args) {
        // 使用第一种 Process 实现类
        ProcessRunner<String, Failure1> runner = new ProcessRunner<>();
        for (int i = 0; i < 3; i++) {
            runner.add(new Processor1());
        }

        try {
            System.out.println(runner.processAll());
        }catch (Failure1 e){
            System.out.println(e);
        }

        // 使用第二种 Process 实现类
        ProcessRunner<Integer, Failure2> runner2 = new ProcessRunner<>();
        for (int i = 0; i < 3; i++) {
            runner2.add(new Processor2());
        }

        try {
            System.out.println(runner2.processAll());
        }catch (Failure2 e){
            System.out.println(e);
        }
    }
}
