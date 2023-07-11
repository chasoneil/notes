package com.JAVA并发编程.Lession02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用 condition 实现交替打印 abc
 *
 * 由于 condition 是 配合 lock 使用的
 *
 */
public class ConditionDemo {

    private static int signal = 0;

    private Lock lock = new ReentrantLock();

    private Condition threadA = lock.newCondition();

    private Condition threadB = lock.newCondition();

    private Condition threadC = lock.newCondition();

    public static void main(String[] args) {

        ConditionDemo demo = new ConditionDemo();
        demo.print();

    }

    private void print() {

        Thread printA = new Thread(() -> {

            while (true) {
                lock.lock();
                if (signal != 0) {
                    try {
                        threadA.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A");
                signal = 1;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadB.signal();
                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread printB = new Thread(() -> {

            while (true) {
                lock.lock();
                if (signal != 1) {
                    try {
                        threadB.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B");
                signal = 2;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadC.signal();
                lock.unlock();

            }
        });

        Thread printC = new Thread(() -> {

            while (true) {
                lock.lock();
                if (signal != 2) {
                    try {
                        threadC.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("C");
                signal = 0;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadA.signal();
                lock.unlock();

            }
        });

        printA.start();
        printB.start();
        printC.start();

    }


}
