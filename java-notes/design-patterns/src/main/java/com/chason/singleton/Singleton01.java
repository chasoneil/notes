package com.chason.singleton;

/**
 *
 * 饿汉 ： 在类的加载时初始化一个静态私有实例
 *
 * 保证实例创建过程是线程安全的
 *
 * 不支持延时加载
 *
 */
public class Singleton01 {

    private static Singleton01 instance = new Singleton01();

    /**
     * 防止反射造成单例模式的破坏
     */
    private Singleton01() {
        if (instance != null) {
            throw new RuntimeException("Illegal Operate for \"private\"");
        }
    }

    public static Singleton01 getInstance() {
        return instance;
    }

}
