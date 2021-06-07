package com.practise.enumerated;

import static com.practise.enumerated.Outcome.*;

public enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;

    // 可以理解为用一张二维表列举出游戏结果
    private static Outcome[][] table = {
            {DRAW, LOSE, WIN}, // PAPER
            {WIN, DRAW, LOSE}, // SCISSORS
            {LOSE, WIN, DRAW}  // ROCk
    };


    @Override
    public Outcome compete(RoShamBo6 competitor) {
        return table[this.ordinal()][competitor.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 10);
    }
}
