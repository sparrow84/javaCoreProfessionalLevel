package com.company.lesson01;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    private ArrayList<T> box;
    private float weight;

    public Box () {
        weight = 0f;
        box = new ArrayList<T>();
    }

    public Box (T ... object) {
        this();
        for (T t: object) {
            this.put(t);
        }
    }

    public void shift(Box otherBox) {
        while (!this.isEmpty()) {
            otherBox.put(this.getOne());
        }
    }

    public boolean compare(Box otherBox) {
        return this.weight == otherBox.weight;
    }

    public int amount() {
        return box.size();
    }

    public void put(T object) {
        weight += object.getWeight();
        box.add(object);
    }

    public T getOne() {
        T result = null;

        if (!this.isEmpty()) {
            weight -= box.get(0).getWeight();
            result = box.get(0);
            box.remove(0);
        } else {
            System.out.println("Box is empty.");
        }
        return result;
    }

    public boolean isEmpty() {
        return !(box.size() > 0);
    }

    public Float getWeight(){
        return this.weight;
    }
}
