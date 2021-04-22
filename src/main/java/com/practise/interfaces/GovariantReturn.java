package com.practise.interfaces;

public class GovariantReturn {

    public static void main(String[] args) {
        // 调用父类 Mill 的 process() 方法返回父类 Grain
        Mill mill = new Mill();
        Grain grain = mill.process();
        System.out.println(grain.toString());
        // 调用子类 WheatMill 的 process() 方法返回子类 Wheat
        mill = new WheatMill();
        grain = mill.process();
        System.out.println(grain.toString());
    }
}

class Grain {
    @Override
    public String toString() {
        return "Grain";
    }
}

class Wheat extends Grain {
    @Override
    public String toString() {
        return "Wheat";
    }
}

class Mill {
    Grain process() {
        return new Grain();
    }
}

class WheatMill extends Mill {
    @Override
    Wheat process() {
        return new Wheat();
    }
}
