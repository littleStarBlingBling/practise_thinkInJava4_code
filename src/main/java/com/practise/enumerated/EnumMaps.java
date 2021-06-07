package com.practise.enumerated;

import java.util.EnumMap;
import java.util.Map;

import static com.practise.enumerated.AlarmPoints.*;

interface Command {
    void action();
}

public class EnumMaps {

    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> enumMaps = new EnumMap<>(AlarmPoints.class);
        // 匿名内部类
        enumMaps.put(KITCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("Kitchen fire!");
            }
        });

        enumMaps.put(BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("Bathroom alert!");
            }
        });

        for(Map.Entry<AlarmPoints, Command> entry : enumMaps.entrySet()){
            System.out.print(entry.getKey() + ": ");
            entry.getValue().action();
        }

        // enumMaps 中没有为 UTILITY 设置值，只有默认的 null
        try{
            enumMaps.get(UTILITY).action();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}