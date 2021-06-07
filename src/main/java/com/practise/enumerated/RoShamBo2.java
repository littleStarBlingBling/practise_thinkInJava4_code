package com.practise.enumerated;

import static com.practise. enumerated.Outcome.*;

public enum RoShamBo2 implements Competitor<RoShamBo2> {
    /**
     * 枚举类 RoShamBo2 实现了 Competitor 接口
     * 枚举常量值是依次与 paper、scissors、rock 比较的结果
     */
    PAPER(DRAW, LOSE, WIN),
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);

    private Outcome vPAPER, vSCISSORS, vROCK;

    RoShamBo2(Outcome paper, Outcome scissors, Outcome rock){
        this.vPAPER = paper;
        this.vSCISSORS = scissors;
        this.vROCK = rock;
    }

    @Override
    public Outcome compete(RoShamBo2 it){
        switch (it){
            default:
            case PAPER: return vPAPER;
            case SCISSORS: return vSCISSORS;
            case ROCK: return vROCK;
        }
    }

    public static void main(String[] args) {
        // play -> match -> compete -> switch
        RoShamBo.play(RoShamBo2.class, 10);
    }
}
