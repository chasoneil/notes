package com.chason.singleton;

/**
 * 静态内部类
 *
 * 静态类只有被使用的时候才会加载  （自带延时加载）
 */
public class Singleton05 {

    private Singleton05 () {}

    /**
     * 静态内部类中创建的单例
     *
     * 在装载内部类的时候才会创建对象
     *
     */
    private static class Singleton05Handler {
        private static Singleton05 instance = new Singleton05();
    }

    public static Singleton05 getInstance() {
        return Singleton05Handler.instance;
    }
}
