package com.company.lesson01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lesson01 {
    public static void main(String[] args) {
        /**
         * 1 - 2 tasks
         * */
        System.out.println("Start ...");
        String[] strings = {"111", "222", "333"};
        System.out.println(Arrays.toString(strings));
        swap(strings,1,2);
        System.out.println(Arrays.toString(strings));

//        int[] arrInt = {1,2,3};
//        System.out.println(Arrays.toString(arrInt));
//        swap(arrInt,1,2);
//        System.out.println(Arrays.toString(arrInt));


        ArrayList<String> arrayList = toArrayList(strings);

        for (String s: arrayList) {
            System.out.print(s + "  ");
        }
        System.out.println("\n");

        /**
         * 3 task
         * */

        Box<Apple> appleBox = new Box<Apple>(new Apple(), new Apple(), new Apple(), new Apple());
        Box<Orange> orangeBox = new Box<Orange>(new Orange(), new Orange(), new Orange());
        Box<Orange> orangeBox01 = new Box<Orange>(new Orange(), new Orange(), new Orange());

        Apple apple01 = new Apple();
        Orange orange001 = new Orange();

        appleBox.put(apple01);
        orangeBox.put(orange001);

        System.out.println("appleBox.getWeight " + appleBox.getWeight());
        System.out.println("orangeBox.getWeight " + orangeBox.getWeight());
        System.out.println("orangeBox01.getWeight " + orangeBox01.getWeight());

        System.out.println("compare appleBox & orangeBox - " + appleBox.compare(orangeBox));

        orangeBox.shift(orangeBox01);
        System.out.println("orangeBox.getWeight " + orangeBox.getWeight());
        System.out.println("orangeBox01.getWeight " + orangeBox01.getWeight());
    }

    public static <E> void swap(E[] e, int i, int j) {
        E tmp = e[i];
        e[i] = e[j];
        e[j] = tmp;
    }

    public static <T> ArrayList<T> toArrayList(T[] arr) {
        ArrayList<T> arrayList = new ArrayList<T>();
        Collections.addAll(arrayList, arr);
        return arrayList;
    }
}
