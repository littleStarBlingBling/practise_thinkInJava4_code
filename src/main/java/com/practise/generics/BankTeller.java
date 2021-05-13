package com.practise.generics;

import java.util.*;

// 顾客类
class Customer {

    private static long counter = 1;
    private final long id = counter++;

    private Customer() {
    }

    @Override
    public String toString() {
        return "Customer " + id;
    }

    // 方法内部匿名类
    public static Generator<Customer> generator() {
        return new Generator<Customer>() {
            @Override
            public Customer next() {
                return new Customer();
            }
        };
    }
}

// 柜员类
class Teller {
    private static long counter = 1;
    private final long id = counter++;

    private Teller() { }

    @Override
    public String toString() {
        return "Teller " + id;
    }

    // 匿名类直接赋值给字段
    public static Generator<Teller> generator = new Generator<Teller>() {
        @Override
        public Teller next() {
            return new Teller();
        }
    };
}


public class BankTeller {

    public static void serve(Teller teller, Customer customer) {
        System.out.println(teller + " serves " + customer);
    }

    public static void main(String[] args) {
        Random random = new Random(8);

        Queue<Customer> line = new LinkedList<>();
        Generators.fill(line, Customer.generator(), 8);

        List<Teller> tellers = new ArrayList<>();
        Generators.fill(tellers, Teller.generator, 2);

        for (Customer customer : line) {
            serve(tellers.get(random.nextInt(tellers.size())), customer);
        }
    }
}
