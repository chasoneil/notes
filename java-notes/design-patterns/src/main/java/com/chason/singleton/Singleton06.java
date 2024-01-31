package com.chason.singleton;

import java.io.Serializable;

/**
 * 双重检查锁的单例  但是支持序列化
 * 会被序列化破坏单例
 */
public class Singleton06 implements Serializable {

    private static volatile Singleton06 instance;

    private Singleton06 () {}

    public static Singleton06 getInstance() {

        if (instance == null) {
            synchronized (Singleton06.class) {

                if (instance == null) {
                    instance = new Singleton06();
                }
            }
        }
        return instance;
    }

    /**
     * solve serialize destroy singleton
     * method name must be readResolve
     * @return
     */
    private Object readResolve () {
        return instance;
    }

}
