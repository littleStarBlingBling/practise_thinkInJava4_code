package com.practise.arrays;

import com.practise.generics.Generator;

import java.util.Random;

public class RandomGenerator {
    private static Random random = new Random(47);

    // 随机生成布尔类型
    public static class Boolean implements Generator<java.lang.Boolean> {
        @Override
        public java.lang.Boolean next() {
            return random.nextBoolean();
        }
    }

    // 随机生成字节类型
    public static class Byte implements Generator<java.lang.Byte> {
        @Override
        public java.lang.Byte next() {
            return (byte) random.nextInt();
        }
    }

    static char[] chars = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    // 随机生成字符类型
    public static class Character implements Generator<java.lang.Character> {
        @Override
        public java.lang.Character next() {
            return chars[random.nextInt(chars.length)];
        }
    }

    // 随机生成字符串类型
    public static class String implements Generator<java.lang.String> {
        private int length = 7;

        Generator<java.lang.Character> characterGenerator = new Character();

        public String() {
        }

        public String(int length) {
            this.length = length;
        }

        // 通过字符随机生成器，一个字符一个字符的生成，组合成字符串
        @Override
        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++) {
                buf[i] = characterGenerator.next();
            }
            return new java.lang.String(buf);
        }
    }

    // 随机生成整数类型
    public static class Integer implements Generator<java.lang.Integer> {
        private int mod = 10000;

        public Integer() {
        }

        public Integer(int mod) {
            this.mod = mod;
        }

        @Override
        public java.lang.Integer next() {
            return random.nextInt(mod);
        }
    }

    // 随机生成 Short 类型
    public static class Short implements Generator<java.lang.Short> {

        @Override
        public java.lang.Short next() {
            return (short) random.nextInt();
        }
    }

    // 随机生成 Long 类型
    public static class Long implements Generator<java.lang.Long> {
        private int mod = 10000;

        public Long() {
        }

        public Long(int mod) {
            this.mod = mod;
        }

        @Override
        public java.lang.Long next() {
            return (long) (random.nextInt(mod));
        }
    }

    // 随机生成 Float 类型
    public static class Float implements Generator<java.lang.Float> {
        @Override
        public java.lang.Float next() {
            int trimmed = Math.round(random.nextFloat() * 100);
            return ((float) trimmed) / 100;
        }
    }

    // 随机生成 Double 类型
    public static class Double implements Generator<java.lang.Double> {
        @Override
        public java.lang.Double next() {
            long trimmed = Math.round(random.nextDouble() * 100);
            return ((double) trimmed) / 100;
        }
    }
}
