package com.JAVA并发编程.Lession02;

/**
 *  使用 wait notifyAll 实现多线程交替打印 ABC
 *
 */
public class WaitNotifyAllDemo {

    private int signal = 0;

    public synchronized void printA() {
        while (signal != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal = 1;
        notifyAll();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printB() {
        while (signal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal = 2;
        notifyAll();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printC() {
        while (signal != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal = 0;
        notifyAll();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WaitNotifyAllDemo printAbcDemo_1 = new WaitNotifyAllDemo();
        Thread printAThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    printAbcDemo_1.printA();
                }
            }
        });
        Thread printBThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    printAbcDemo_1.printB();
                }
            }
        });
        Thread printCThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    printAbcDemo_1.printC();
                }
            }
        });
        printAThread.start();
        printBThread.start();
        printCThread.start();
        Thread.yield();
    }


}
