package com.company.lesson01;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    private ArrayList<T> box;
    private Float weight;

    public Box () {
        weight = 0f;
        box = new ArrayList<T>();
    }

    public Box (T ... fruit) {
        this();
        for (T t: fruit) {
            this.put(t);
        }
    }

    public void put(T fruit) {
        box.add(fruit);
        weight += fruit.getWeight();
    }

    public Float getWeight(){

        return 0.1f;
    }
}
