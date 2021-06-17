package com.practise.annotations;

@ExtractInterface(interfaceName = "IMultiplier")
public class Multiplier {

    public boolean flag = false;
    private int n = 0;


    // 通过循环相加来实现乘法
    public int multiplier(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++) {
            total = add(total, y);
        }

        return total;
    }

    public int fortySeven() {
        return 47;
    }

    private int add(int x, int y) {
        return x + y;
    }

    public double timesTen(double arg) {
        return arg * 10;
    }

    public static void main(String[] args) {
        Multiplier multiplier = new Multiplier();
        System.out.println("11 * 16 = " + multiplier.multiplier(11, 16));
    }
}
