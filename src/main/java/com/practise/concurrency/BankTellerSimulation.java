package com.practise.concurrency;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

class Customer{
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

class CustomerLine extends ArrayBlockingQueue<Customer>{
    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }

    @Override
    public String toString() {
        if (this.size() == 0){
            return "[Empty]";
        }

        StringBuilder result = new StringBuilder();
        for(Customer customer : this){
            result.append(customer);
        }

        return result.toString();
    }
}

class CustomerGenerator implements Runnable{
    private CustomerLine customers;
    private static Random random = new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.put(new Customer(random.nextInt(1000)));
            }
        }catch (InterruptedException r){
            System.out.println("CustomerGenerator interrupted");
        }

        System.out.println("CustomerGenerator terminating");
    }
}

//class Teller implements Runnable, Comparator<Teller>{
//    private static int counter = 0;
//    private final  int id = counter++;
//    private int customerServerd = 0;
//    private CustomerLine customers;
//    private boolean servingCustomerLine = true;
//
//    public Teller(CustomerLine customers) {
//        this.customers = customers;
//    }
//}

public class BankTellerSimulation {
}
