package com.practise.enumerated;

import java.util.EnumMap;
import static com.practise.enumerated.Outcome.*;

public enum RoShamBo5 implements Competitor<RoShamBo5> {

    PAPER, SCISSORS, ROCK;

    // 直接用 EnumMap 保存比较结果
    static EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>> table = new EnumMap<>(RoShamBo5.class);

    // 在枚举类的构造语句中计算比较结果
    static {
        for (RoShamBo5 it : RoShamBo5.values()) {
            table.put(it, new EnumMap<>(RoShamBo5.class));
        }

        // 直接按顺序配置比较结果
        // PAPER vs (PAPER, SCISSORS, ROCk)
        initRow(PAPER, DRAW, LOSE, WIN);
        initRow(SCISSORS, WIN, DRAW, LOSE);
        initRow(ROCK, LOSE, WIN, DRAW);
    }

    static void initRow(RoShamBo5 it, Outcome vPAPER, Outcome vSCISSORS, Outcome vROCK) {
        EnumMap<RoShamBo5, Outcome> row = RoShamBo5.table.get(it);
        row.put(RoShamBo5.PAPER, vPAPER);
        row.put(RoShamBo5.SCISSORS, vSCISSORS);
        row.put(RoShamBo5.ROCK, vROCK);
    }

    // 两路分发
    @Override
    public Outcome compete(RoShamBo5 it) {
        return table.get(this).get(it);
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo5.class, 10);
    }
}
