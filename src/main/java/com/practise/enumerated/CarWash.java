package com.practise.enumerated;

import java.util.EnumSet;

public class CarWash {

    public enum Cycle{
        // 每一个字符常量都有自己的行为
        UNDER_BODY {
            @Override
            void action(){
                System.out.println("Spraying the underbody");
            }
        },

        WHEEL_WASH {
            @Override
            void action(){
                System.out.println("Washing the wheels");
            }
        },
        PRE_WASH {
            @Override
            void action(){
                System.out.println("Loosening the dirt");
            }
        },

        BASIC{
            @Override
            void action(){
                System.out.println("The basic wash");
            }
        },

        HOT_WAX {
            @Override
            void action(){
                System.out.println("Applying hot wax");
            }
        },

        RINSE{
            @Override
            void action(){
                System.out.println("Rinsing");
            }
        },

        BLOW_DRY {
            @Override
            void action(){
                System.out.println("Blowing dry");
            }
        };

        abstract void action();
    }

    // 默认套餐
    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public void add(Cycle cycle){
        cycles.add(cycle);
    }

    // 遍历加入的节点，依次执行对应的行为
    public void washCar(){
        for(Cycle cycle : cycles){
            cycle.action();
        }
    }

    @Override
    public String toString() {
        return cycles.toString();
    }

    public static void main(String[] args) {
        CarWash wash = new CarWash();
        System.out.println(wash);

        wash.washCar();

        wash.add(Cycle.BLOW_DRY);
        // 因为是set，重复添加会被忽略
        wash.add(Cycle.BLOW_DRY);
        wash.add(Cycle.RINSE);
        wash.add(Cycle.HOT_WAX);
        // 因为是由 enum 实例定义的顺序决定，添加入 set 的顺序没有影响
        System.out.println(wash);

        wash.washCar();
    }
}
