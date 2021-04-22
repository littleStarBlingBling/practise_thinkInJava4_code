package com.practise.interfaces;

class Egg2{

    class Yolk{
        public Yolk(){
            System.out.println("Egg2.Yolk()");
        }

        public void f(){
            System.out.println("Egg2.Yolk.f()");
        }
    }

    private Yolk yolk = new Yolk();

    public Egg2(){
        System.out.println("New Egg2()");
    }

    public void insertYolk(Yolk yolk){
        this.yolk = yolk;
    }

    public void g(){
        yolk.f();
    }

}

public class BigEgg2 extends Egg2{
    // 继承自外围类的父类的内部类
    public class Yolk extends Egg2.Yolk{
        public Yolk(){
            System.out.println("BigEgg2.Yolk()");
        }

        @Override
        public void f(){
            System.out.println("BigEgg2.Yolk.f()");
        }
    }

    public BigEgg2(){
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        // 首先是 BigEgg2 的构造器，触发父类 Egg2 的构造器：父类 Egg2 中的 yolk 字段初始化 -> 父类 Egg2 的构造器
        // 接着执行构造器中的内容，new Yolk()：父类 Egg2 中内部类 Yolk 的构造器 -> 自身内部类 Yolk 的构造器
        Egg2 egg2 = new BigEgg2();
        // 调用 BigEgg2 的内部类的 f() 方法
        egg2.g();
    }
}
