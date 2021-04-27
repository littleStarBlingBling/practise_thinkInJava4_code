package com.practise.containers;

public class AssociativeArray<K, V> {
    // 用二维数组来保存键值对
    // 也就是两行的表格，第一行 key 第二行 value
    private Object[][] pairs;

    private int index;

    public AssociativeArray(int length) {
        pairs = new Object[length][2];
    }

    public void put(K key, V value) {
        if (index >= pairs.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        pairs[index++] = new Object[]{key, value};
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        for (int i = 0; i < index; i++) {
            if (key.equals(pairs[i][0])) {
                return (V) pairs[i][1];
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++) {
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());
            if (i < index - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        AssociativeArray<String, String> map = new AssociativeArray<>(3);
        map.put("sky","blue");
        map.put("grass","green");
        map.put("sun","warm");
        System.out.println(map);
    }
}
