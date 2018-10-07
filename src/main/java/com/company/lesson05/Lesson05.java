package com.company.lesson05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lesson05 {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        CyclicBarrier allCarsRady = new CyclicBarrier(CARS_COUNT);

        final CountDownLatch raceStart = new CountDownLatch(CARS_COUNT);

        final CountDownLatch raceEnd   = new CountDownLatch(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40));

        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), allCarsRady , raceEnd, raceStart);
        }



        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        new Thread(() -> {
            try {
                raceStart.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }).start();

        new Thread(() -> {
            try {
                raceEnd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }).start();


    }
}

