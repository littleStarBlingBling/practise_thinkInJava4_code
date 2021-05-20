package com.practise.io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class AvailableCharSets {

    public static void main(String[] args) {

        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();

        // 遍历 SortedMap
        while (it.hasNext()){
            String csName  = it.next();
            System.out.print(csName);
            // 遍历对应编码的别名
            Iterator aliases = charSets.get(csName).aliases().iterator();
            if(aliases.hasNext()){
                System.out.print(": ");
            }

            while(aliases.hasNext()){
                System.out.print(aliases.next());
                if(aliases.hasNext()){
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }
}
