package com.practise.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Address{
    private String s;

    public Address(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString() + " " + s;
    }
}

public class FillingList {
    public static void main(String[] args) {
        List<Address> list = new ArrayList<>(Collections.nCopies(3, new Address("Hello")));
        System.out.println(list);

        Collections.fill(list, new Address("good"));
        System.out.println(list);
    }
}
