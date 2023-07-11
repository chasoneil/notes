package com.JAVA并发编程.Lession02;

import org.openjdk.jol.info.ClassLayout;

/**
 * synchronized 关键字和对象头的研究
 *
 * 总结: JDK11 的锁升级机制已经更新了 推荐使用JDK1.6版本的锁升级机制
 */
public class ObjectHeadDemo {


    public static void main(String[] args) throws Throwable {


        Object lock = new Object();
        System.out.println("No lock:" + ClassLayout.parseInstance(lock).toPrintable());

//        Thread.sleep( 4 * 1000);
//
//        System.out.println("Thread ID:" + Thread.currentThread().getId());
//        System.out.println("偏向锁状态:" + ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            System.out.println("Thread ID:" + Thread.currentThread().getId());
            System.out.println("偏向锁状态:" + ClassLayout.parseInstance(lock).toPrintable());
        }

    }



}
