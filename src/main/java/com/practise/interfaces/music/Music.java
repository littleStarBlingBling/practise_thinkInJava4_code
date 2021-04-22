package com.practise.interfaces.music;

class Instrument {
    void play(String note) {
        System.out.println("Instrument.play() " + note);
    }
}

class Wind extends Instrument {
    @Override
    void play(String note) {
        System.out.println("Wind.play() " + note);
    }
}

class Brass extends Instrument {
    @Override
    void play(String note) {
        System.out.println("Brass.play() " + note);
    }
}

public class Music {
    public static void tune(Instrument i) {
        i.play("Music Name");
    }

    public static void tuneAll(Instrument[] e) {
        for (Instrument i : e) {
            tune(i);
        }
    }

    public static void main(String[] args) {
        Instrument[] orchestra = {
                new Wind(),
                new Brass()
        };
        tuneAll(orchestra);
    }
}
