package com.practise.containers;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

// 只有 equals 方法
class SetType {

    int i;

    public SetType(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SetType && (i == ((SetType) o).i);
    }

    @Override
    public String toString() {
        return Integer.toString(i);
    }
}

// 有 equals 和 hashCode 方法
// 但是 hashCode 并没有正确编写
class HashType extends SetType {

    public HashType(int i) {
        super(i);
    }

    @Override
    public int hashCode() {
        return i;
    }
}


// 有 equals、hashCode 和 compareTo 方法
class TreeType extends SetType implements Comparable<TreeType> {

    public TreeType(int i) {
        super(i);
    }

    @Override
    public int compareTo(TreeType o) {
        return Integer.compare(o.i, i);
    }
}


public class TypesForSets {

    // 填充 int 类型的数据
    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for (int i = 0; i < 6; i++) {
                set.add(type.getConstructor(int.class).newInstance(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return set;
    }

    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);

        // 尝试增加重复的数据
        fill(set, type);
        System.out.println(set);
    }

    public static void main(String[] args) {
        // HashType 有 equals 和 hashCode 方法
        test(new HashSet<>(), HashType.class);
        test(new LinkedHashSet<>(), HashType.class);
        // TreeType 有 equals、hashCode 和 compareTo 方法
        test(new TreeSet<>(), TreeType.class);

        // SetType 只有 equals 方法
        // 无论是否提供了 equals、compare 方法，hashcode 方法没有正常编写就会有重复数据的问题
        test(new HashSet<>(), SetType.class);
        test(new HashSet<>(), TreeType.class);
        test(new LinkedHashSet<>(), SetType.class);
        test(new LinkedHashSet<>(), TreeType.class);

        try {
            test(new TreeSet<>(), SetType.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            test(new TreeSet<>(), HashType.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
