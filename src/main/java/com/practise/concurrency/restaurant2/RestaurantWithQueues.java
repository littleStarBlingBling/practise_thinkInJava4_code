package com.practise.concurrency.restaurant2;


import com.practise.enumerated.menu.Course;
import com.practise.enumerated.menu.Food;

import java.util.concurrent.SynchronousQueue;

//class Order {
//    private static int counter = 0;
//    private final int id = counter++;
//    private final Customer customer;
//    private final WaitPerson waitPerson;
//    private final Food food;
//
//    public Order(Customer customer, WaitPerson waitPerson, Food food) {
//        this.customer = customer;
//        this.waitPerson = waitPerson;
//        this.food = food;
//    }
//
//    public Food item() {
//        return food;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public WaitPerson getWaitPerson() {
//        return waitPerson;
//    }
//
//    @Override
//    public String toString() {
//        return "Order: " + id + " item: " + food + " for: " + customer + " serverd by: " + waitPerson;
//    }
//}

//class Plate {
//    private final Order order;
//    private final Food food;
//
//    private Plate(Order order, Food food) {
//        this.order = order;
//        this.food = food;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public Food getFood() {
//        return food;
//    }
//
//    @Override
//    public String toString() {
//        return food.toString();
//    }
//}

//class Customer implements Runnable {
//    private static int counter = 0;
//    private final int id = counter++;
//    private final WaitPerson waitPerson;
//    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();
//
//    public Customer(WaitPerson waitPerson) {
//        this.waitPerson = waitPerson;
//    }
//
//    public void deliver(Plate plate)throws InterruptedException{
//        placeSetting.put(plate);
//    }
//
//    @Override
//    public void run() {
//        for(Course course : Course.values()){
//            Food food =
//        }
//    }
//}

//class WaitPerson implements Runnable {
//}
//
//class Chef implements Runnable {
//}
//
//class Restaurant implements Runnable {
//}

public class RestaurantWithQueues {
}
