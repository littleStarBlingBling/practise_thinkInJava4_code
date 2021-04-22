package com.practise.interfaces;

class Egg {
    private Yolk yolk;

    class Yolk {
        public Yolk() {
            System.out.println("Egg Yolk()");
        }

    }

    // 外围类的构造器中调用了内部类的构造器
    public Egg() {
        System.out.println("New Egg()");
        yolk = new Yolk();
    }
}

public class BigEgg extends Egg{

    public class Yolk {
        public Yolk() {
            System.out.println("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        new BigEgg();
    }
}
