package com.practise.io;

import java.io.Serializable;

public class Alien implements Serializable {
    private int id;

    public Alien() { }

    public Alien(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
