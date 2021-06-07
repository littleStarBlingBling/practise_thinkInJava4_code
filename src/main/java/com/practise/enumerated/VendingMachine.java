package com.practise.enumerated;

import com.practise.generics.Generator;
import com.practise.io.TextFile;
import com.practise.utils.ConstUtils;

import java.util.EnumMap;
import java.util.Iterator;

import static com.practise.enumerated.Input.*;

enum Category {
    // 将 Input 进行分组
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private Input[] values;

    Category(Input... types) {
        values = types;
    }

    private static EnumMap<Input, Category> categoryEnumMap = new EnumMap<>(Input.class);

    static {
        for (Category category : Category.class.getEnumConstants()) {
            for (Input input : category.values) {
                categoryEnumMap.put(input, category);
            }
        }
    }

    // 根据输出取到行为分类，然后改变售货机状态
    public static Category categorize(Input input) {
        return categoryEnumMap.get(input);
    }

}


class RandomInputGenerator implements Generator<Input> {

    private int count = 0;

    // 设置成只支持 10 次操作
    @Override
    public Input next() {
        if (count++ > 10) {
            return STOP;
        }
        return Input.randomSelection();
    }
}

class FileInputGenerator implements Generator<Input> {
    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    @Override
    public Input next() {
        if (!input.hasNext()) {
            return null;
        }

        return Enum.valueOf(Input.class, input.next().trim());
    }
}

// 自动售货机
public class VendingMachine {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static Input selection = null;

    // 用来做标记的枚举 : 瞬时状态
    enum StateDuration {
        TRANSIENT
    }

    enum State {
        // 首先从休息状态 -> 投币 或 关闭
        RESTING {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        // 投币状态
        ADDING_MONEY {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    // 增加金钱，状态不变
                    case MONEY:
                        amount += input.amount();
                        break;
                    // 成功选择商品 -> 分配商品状态
                    case ITEM_SELECTION:
                        selection = input;
                        // 钱不够
                        if (amount < selection.amount()) {
                            System.out.println("Insufficient money for " + selection + "(" + selection.value + ")");
                        } else {
                            state = DISPENSING;
                        }
                        break;
                    // 退出交易 -> 返回金钱状态
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        // 分配商品状态是一个瞬时状态
        DISPENSING(StateDuration.TRANSIENT) {
            @Override
            void next() {
                System.out.println("here is your " + selection + "(" + selection.value + ")");
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        // 给商品 -> 找钱 -> 休息
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            @Override
            void next() {
                if (amount > 0) {
                    System.out.println("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            @Override
            void output() {
                System.out.println("Halted");
            }
        };

        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(Input input) {
            throw new RuntimeException("Only call next(Input input) for no-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            System.out.println("now the money is : " + amount);
        }

        static void run(Generator<Input> generator) {
            while (state != State.TERMINAL) {
                state.next(generator.next());
                while (state.isTransient) {
                    state.next();
                }
                state.output();
            }
        }

        public static void main(String[] args) {
            // 使用两种方式来测试售货机程序
            Generator<Input> generator = new RandomInputGenerator();
//			run(generator);

            generator = new FileInputGenerator(ConstUtils.OUT_PREFIX + "VendingMachineInput.txt");
            run(generator);
        }

    }
}
