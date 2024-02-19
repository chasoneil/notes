package com.chason.singleton;

/**
 * 懒汉式 01  支持延迟加载（懒加载）
 *
 * 但是线程不安全
 *
 */
public class Singleton02 {

    private static Singleton02 instance;

    private Singleton02 () {}

    public static Singleton02 getInstance() {

        // 多线程的时候会有线程安全的问题
        if (instance == null) {
            instance = new Singleton02();
        }

        return instance;
    }
}
