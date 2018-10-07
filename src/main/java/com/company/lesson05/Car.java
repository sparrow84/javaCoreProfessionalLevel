package com.company.lesson05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Car implements Runnable {

    CyclicBarrier cyclicbarrier;
    Lock raceEnd;
    Lock raceStart;

    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cyclicbarrier, Lock raceEnd, Lock raceStart) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cyclicbarrier = cyclicbarrier;
        this.raceEnd = raceEnd;
        this.raceStart = raceStart;
    }

    @Override
    public void run() {

        raceEnd.tryLock();
        raceStart.tryLock();

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            cyclicbarrier.await();

            try {
                raceStart.unlock();
            } catch (IllegalMonitorStateException e) {
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        //FIXME

        try {
            raceEnd.unlock();
        } catch (IllegalMonitorStateException e) {
        }

    }
}
