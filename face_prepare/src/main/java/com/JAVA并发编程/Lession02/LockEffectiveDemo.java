package com.JAVA并发编程.Lession02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁效率
 * 1. 10个线程，每个加1000次  没有并发控制
 *
 *
 */
public class LockEffectiveDemo {

    private static int count = 0;

    private static CountDownLatch latch = new CountDownLatch(100);     // max threads are 10

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Throwable {

        System.out.println("before:" + count);
        long before = System.currentTimeMillis();
        doThreadAdd();
        latch.await();

        long after = System.currentTimeMillis();
        System.out.println("after:" + count);
        System.out.println("spent:" + (after - before) + "ms");

    }

    private static void doThreadAdd() {

        for (int i=0; i<100; i++) {
            new Thread(() -> {
                for (int j=0; j<100000; j++) {
                    // doCountIncrement();

                    doCountIncrementByLock();
                    //count++;
                }
                latch.countDown();
            }).start();
        }
    }


    private static synchronized void doCountIncrement() {
        count++;
    }

    private static void doCountIncrementByLock() {

        lock.lock();
        count++;
        lock.unlock();

    }


}
