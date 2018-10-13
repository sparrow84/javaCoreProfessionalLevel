package com.company.lesson05;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private int nopatst; // numberOfParticipantsAtTheSameTime
    Semaphore semaphore;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    public Tunnel(int numberOfParticipantsAtTheSameTime) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.nopatst = numberOfParticipantsAtTheSameTime;
        semaphore = new Semaphore(nopatst);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);

                semaphore.acquire();



                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
