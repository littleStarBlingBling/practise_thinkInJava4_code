package com.practise.generics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// 处理数据的接口
interface Combiner<T> {
    T combine(T x, T y);
}

// 一元函数接口
interface UnaryFunction<R, T> {
    R function(T x);
}

// 收集结果的接口
interface Collector<T> extends UnaryFunction<T, T> {
    T result();
}

// 一元断言
interface UnaryPredicate<T> {
    boolean test(T x);
}

public class Functional {

    // 遍历序列，依次执行传入的处理器
    public static <T> T reduce(Iterable<T> seq, Combiner<T> combiner) {
        Iterator<T> it = seq.iterator();
        if (it.hasNext()) {
            T result = it.next();
            while (it.hasNext()) {
                result = combiner.combine(result, it.next());
            }
            return result;
        }

        // 如果序列为空则返回 null，也可以抛异常
        return null;

    }

    // 遍历序列，执行一元函数，返回收集到的 Collector
    public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func) {
        for (T t : seq) {
            func.function(t);
        }
        return func;
    }

    // 遍历序列，执行一元函数，并返回执行结果的集合
    public static <R, T> List<R> transform(Iterable<T> seq, UnaryFunction<R, T> function) {
        List<R> result = new ArrayList<>();
        for (T t : seq) {
            result.add(function.function(t));
        }
        return result;
    }

    // 使用一元断言来进行过滤，返回符合的结果集
    public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : seq) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }


    // 整数加法处理器
    static class IntegerAdder implements Combiner<Integer> {
        @Override
        public Integer combine(Integer x, Integer y) {
            return x + y;
        }
    }

    // 整数减法处理器
    static class IntegerSubstracter implements Combiner<Integer> {
        @Override
        public Integer combine(Integer x, Integer y) {
            return x - y;
        }
    }

    // BigDecimal 的加法处理器
    static class BigDecimalAdder implements Combiner<BigDecimal> {
        @Override
        public BigDecimal combine(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    }

    // BigInteger 的加法处理器
    static class BigIntegerAdder implements Combiner<BigInteger> {
        @Override
        public BigInteger combine(BigInteger x, BigInteger y) {
            return x.add(y);
        }
    }

    // AtomicLong 的加法处理器
    static class AtomicLongAddr implements Combiner<AtomicLong> {
        @Override
        public AtomicLong combine(AtomicLong x, AtomicLong y) {
            return new AtomicLong(x.addAndGet(y.get()));
        }
    }

    // 返回此 BigDecimal 最后一个位置的 ulp （一个单位）大小。非零 BigDecimal 值的 ulp 是该值和 BigDecimal 值与数字相同的数量级下一个较大的正距离
    // 例如，1.23 的 ulp 是 0.01
    static class BigDecimalUlp implements UnaryFunction<BigDecimal, BigDecimal> {
        @Override
        public BigDecimal function(BigDecimal x) {
            return x.ulp();
        }
    }

    // 排序用的一元断言
    static class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T> {

        private T bound;

        public GreaterThan(T bound) {
            this.bound = bound;
        }

        @Override
        public boolean test(T x) {
            return x.compareTo(bound) > 0;
        }
    }

    // 整数乘法
    static class MultiplyingIntegerCollector implements Collector<Integer> {

        private Integer val = 1;

        @Override
        public Integer function(Integer x) {
            val *= x;
            return val;
        }

        @Override
        public Integer result() {
            return val;
        }
    }

    public static void main(String[] args) {
        // ------ Integer ------
        // 求和：1 + 2 + 3 + 4 + 5 + 6
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer result = reduce(list, new IntegerAdder());
        System.out.println(result);
        // 减法：1 - 2 - 3 - 4 - 5 - 6
        result = reduce(list, new IntegerSubstracter());
        System.out.println(result);
        // 过滤出大于 4 的数
        System.out.println(filter(list, new GreaterThan<>(4)));
        // 乘法：1 * 2 * 3 * 4 * 5 * 6
        System.out.println(forEach(list, new MultiplyingIntegerCollector()).result());
        // 先过滤出大于 4 的数，再做乘法
        System.out.println(forEach(filter(list, new GreaterThan<>(4)), new MultiplyingIntegerCollector()).result());

        // ------ BigDecimal ------
        // 小数求和
        // 设置精度为 7
        MathContext mathContext = new MathContext(7);
        List<BigDecimal> bigDecimalList = Arrays.asList(
                new BigDecimal(1.1, mathContext),
                new BigDecimal(2.2, mathContext),
                new BigDecimal(3.3, mathContext),
                new BigDecimal(4.4, mathContext)
        );
        // BigDecimal 求和
        BigDecimal bigDecimal = reduce(bigDecimalList, new BigDecimalAdder());
        System.out.println(bigDecimal);
        // 过滤出大于 3 的数
        System.out.println(filter(bigDecimalList, new GreaterThan<>(new BigDecimal(3))));
        System.out.println(transform(bigDecimalList, new BigDecimalUlp()));

        // ------ BigInteger ------
        // 使用 BigInteger 的质数生成器，然后求和
        List<BigInteger> bigIntegerList = new ArrayList<>();
        BigInteger bigInteger = BigInteger.valueOf(11);
        for (int i = 0; i < 11; i++) {
            bigIntegerList.add(bigInteger);
            // 返回大于当前数值的下一个可能的质数
            bigInteger = bigInteger.nextProbablePrime();
        }
        System.out.println(bigIntegerList);
        // BigInteger 求和：11 + 13 + 17 + 19 + 23 + 29 + 31 + 37 + 41 + 43 + 47
        BigInteger bigInteger2 = reduce(bigIntegerList, new BigIntegerAdder());
        System.out.println(bigInteger2);

        // ------ AtomicLong ------
        List<AtomicLong> atomicLongList = Arrays.asList(
                new AtomicLong(2),
                new AtomicLong(4),
                new AtomicLong(6),
                new AtomicLong(8)
        );
        // AtomicLong 求和：2 + 4 + 6 + 8
        AtomicLong atomicLong = reduce(atomicLongList, new AtomicLongAddr());
        System.out.println(atomicLong);
    }
}
