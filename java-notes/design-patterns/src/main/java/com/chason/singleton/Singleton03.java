package com.chason.singleton;

/**
 * 懒汉
 *
 * 线程安全
 *
 */
public class Singleton03 {

    private static Singleton03 instance;

    private Singleton03 () {}

    /**
     * 通过添加 synchronized 同步锁的方式保证多线程的安全
     *
     * 并发度低
     *
     * @return
     */
    public synchronized static Singleton03 getInstance() {

        if (instance == null) {
            instance = new Singleton03();
        }

        return instance;
    }
}
