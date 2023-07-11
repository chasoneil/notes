package com.JAVA并发编程.Lession02;


import java.util.concurrent.CountDownLatch;

/**
 *
 *  countDownLatch 是一个计数器  countDown 实现计数器自减操作
 *
 *  await 会让线程阻塞  当计数器归零 则await的线程开始执行
 *
 *  countDownLatch 的用法: 初始化计数器，线程执行完可以使用 countDown 做递减
 *  等待的线程使用 await进行等待， 当所有的任务完成之后 countDownLatch 清零则 await 的线程开始运行
 *
 */

public class CountDownLatchDemo {


    private CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws Throwable {

        CountDownLatchDemo demo = new CountDownLatchDemo();

        System.out.println("Start all duty ~");
        demo.doThread();

        demo.countDownLatch.await();
        System.out.println("Finish all duty ~");

    }

    private void doThread() {

        for (int i=0; i<10; i++) {

            new Thread(() -> {

                try {
                    int seconds = (int) (Math.random() * 10 + 1);

                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException e) {

                }
                System.out.println(Thread.currentThread().getName() + " done");
                countDownLatch.countDown();
            }).start();
        }
    }







}
