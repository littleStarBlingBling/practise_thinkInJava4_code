package com.practise.generics;

import java.util.ArrayList;
import java.util.Random;

// 产品类
class Product {

    private final int id;
    private String description;
    private double price;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "id=" + id + " : " + description + ", price: $" + price;
    }

    public static Generator<Product> generator = new Generator<Product>() {
        private Random random = new Random(8);

        @Override
        public Product next() {
            return new Product(random.nextInt(233), "Test", Math.round(random.nextDouble() * 1000.0) + 0.99);
        }
    };
}

// 货架类
class Shelf extends ArrayList<Product> {
    // 货架是一个列表，持有多个产品
    public Shelf(int productNumber) {
        Generators.fill(this, Product.generator, productNumber);
    }
}

// 货廊类
class Aisle extends ArrayList<Shelf> {
    // 货廊持有多个货架
    public Aisle(int shelveNumber, int productNumber) {
        for (int i = 0; i < shelveNumber; i++) {
            add(new Shelf(productNumber));
        }
    }
}

public class Store extends ArrayList<Aisle> {

    // 商店里持有多个货廊
    public Store(int aisleNumber, int shelveNumber, int productNumber) {
        for (int i = 0; i < aisleNumber; i++) {
            add(new Aisle(shelveNumber, productNumber));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Aisle aisle : this) {
            for (Shelf shelf : aisle) {
                for (Product product : shelf) {
                    stringBuilder.append(product);
                    stringBuilder.append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(1, 2, 3));
    }
}
