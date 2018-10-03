package com.company.lesson04;

public class Lesson04 {

    private static final Object monA = new Object();
    private static final Object monB = new Object();
    private static final Object monC = new Object();

    public static void main(String[] args) {
        System.out.println("Start ...");



        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.print("A");
                try {
                    synchronized (monA) {
                        monA.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.print("B");
                try {
                    synchronized (monB) {
                        monB.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.print("C ");
                try {

                    synchronized (monA) {
                        monA.notify();
                    }

                    synchronized (monC) {
                        monC.wait();
                    }



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

/** /
        try {
            synchronized (monA) {
                monA.notify();
            }
            synchronized (monB) {
                monB.wait();
            }
            synchronized (monC) {
                monC.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
/**/

        t1.start();
        t2.start();
        t3.start();





    }
}
