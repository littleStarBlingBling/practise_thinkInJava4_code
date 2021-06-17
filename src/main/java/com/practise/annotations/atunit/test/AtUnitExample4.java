package com.practise.annotations.atunit.test;

import java.util.*;

public class AtUnitExample4 {
    static String theory = "All brontosauruses are thin at one end, much MUCH thicker in the middle, and then thin again at the far end.";

    private String word;
    private Random random = new Random();

    public AtUnitExample4(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public String scrambleWorld() {
        List<Character> chars = new ArrayList<>();
        for(Character c: word.toCharArray()){
            chars.add(c);
        }
        Collections.shuffle(chars, random);

        StringBuilder result = new StringBuilder();
        for (char ch : chars) {
            result.append(ch);
        }

        return result.toString();
    }

    @TestProperty
    static List<String> input = Arrays.asList(theory.split(" "));

    @TestProperty
    static Iterator<String> words = input.iterator();

    @TestObjectCreate
    static AtUnitExample4 create(){
        if(words.hasNext()){
            return new AtUnitExample4(words.next());
        }
        return null;
    }

    @Test
    boolean words(){
        System.out.println("'" + getWord() + "'");
        return getWord().equals("are");
    }

    @Test
    boolean scramble1(){
        random = new Random(47);
        System.out.println("'" + getWord() + "'");
        String scrambled = scrambleWorld();
        System.out.println(scrambled);
        return scrambled.equals("1A1");
    }

    @Test
    boolean scramble2(){
        random = new Random(74);
        System.out.println("'" + getWord() + "'");
        String scrambled = scrambleWorld();
        System.out.println(scrambled);
        return scrambled.equals("tsaeborornussu");
    }
}