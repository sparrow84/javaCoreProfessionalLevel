package com.company.lesson01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class lesson01 {
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

        /**
         * 3 task
         * */

        Box<Apple> appleBox = new Box<Apple>(new Apple(), new Apple(), new Apple());
//        Box

        Apple apple01 = new Apple();
        Orange orange001 = new Orange();

        appleBox.put(apple01);
//        appleBox.put(orange001);

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
