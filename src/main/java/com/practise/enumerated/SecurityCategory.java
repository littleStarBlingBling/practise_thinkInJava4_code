package com.practise.enumerated;

import com.practise.utils.EnumUtils;

// 枚举 - 内部接口 - 枚举
public enum SecurityCategory {
        STOCK(Security.Stock.class),
        BOND(Security.Bond.class);

        Security[] values;

        SecurityCategory(Class<? extends Security> kind) {
        values = kind.getEnumConstants();
        }

interface Security {
    enum Stock implements Security {SHORT, LONG, MARGIN}

    enum Bond implements Security {MUNICIPAL, JUNK}
}

    public Security randomSelection() {
        return EnumUtils.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            SecurityCategory category = EnumUtils.random(SecurityCategory.class);
            System.out.println(category + ": " + category.randomSelection());
        }
    }
}
