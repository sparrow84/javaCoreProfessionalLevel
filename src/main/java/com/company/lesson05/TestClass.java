package com.company.lesson05;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestClass {

    public static final int COUNT = 4;

   public static void main(String[] args) {

       Lock lock = new ReentrantLock();

       new Thread(() -> {
           System.out.println("111");

       }).start();

       new Thread(() -> {
           lock.lock();
           System.out.println("222");
           lock.unlock();
       }).start();

       new Thread(() -> {
           lock.lock();
           System.out.println("333");
           lock.unlock();
       }).start();










//       CyclicBarrier cb = new CyclicBarrier(COUNT);
//       for (int i = 0; i < COUNT; i++) {
//           final int w = i;
//           new Thread(() -> {
//               try {
//                   System.out.println("Поток " + w + " готовится");
//                   Thread.sleep(100 + (int) (3000 * Math.random()));
//                   System.out.println("Поток " + w + " готов");
//                   cb.await();
//                   System.out.println("Поток " + w + " запустился");
//               } catch (Exception e) {
//                   e.printStackTrace();
//               }
//           }).start();
//       }
   }
}

