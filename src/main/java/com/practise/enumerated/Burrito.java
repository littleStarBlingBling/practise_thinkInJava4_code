package com.practise.enumerated;

public class Burrito {
    Spiciness spiciness;

    public Burrito(Spiciness spiciness) {
        this.spiciness = spiciness;
    }

    public void describe() {
        System.out.print("This burrito is ");
        switch (spiciness) {
            case NOT:
                System.out.println("not spicy at all");
                break;
            case MILD:
            case MEDIUM:
                System.out.println("a little hot");
                break;
            case FLAMING:
            default:
                System.out.println("maybe to hot");
        }
    }

    public static void main(String[] args) {
        Burrito
                plain = new Burrito(Spiciness.NOT),
                greenChile = new Burrito(Spiciness.MEDIUM),
                jalapeno = new Burrito(Spiciness.HOT);
        plain.describe();
        greenChile.describe();
        jalapeno.describe();
    }
}
