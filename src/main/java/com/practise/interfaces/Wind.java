package com.practise.interfaces;

class Instrument{
    public void play(){
        System.out.println("play: " + this.getClass());
    }
    static void tune(Instrument i){
        i.play();
    }
}

public class Wind extends Instrument {
    public static void main(String[] args){
        Wind flute = new Wind();
        // 子类 Wind 向上转型成 父类 Instrument
        Instrument.tune(flute);
    }
}
