package com.practise.enumerated;

public enum RoShamBo4 implements Competitor<RoShamBo4> {
    /**
     * 把能战胜的类型、入参传进去
     */
    ROCK {
        @Override
        public Outcome compete(RoShamBo4 opponent) {
            return compete(SCISSORS, opponent);
        }
    },
    SCISSORS {
        @Override
        public Outcome compete(RoShamBo4 opponent) {
            return compete(PAPER, opponent);
        }
    },
    PAPER {
        @Override
        public Outcome compete( RoShamBo4 opponent) {
            return compete(ROCK, opponent);
        }
    };

    // 用了排除法来计算比较结果
    Outcome compete(RoShamBo4 loser, RoShamBo4 opponent) {
        return ((opponent == this) ? Outcome.DRAW : ((opponent == loser) ? Outcome.WIN : Outcome.LOSE));
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo4.class, 10);
    }
}
