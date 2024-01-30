package com.chason.singleton;

/**
 * 懒汉
 *
 * 双重检查锁保证线程安全
 *
 */
public class Singleton04 {


    private static volatile Singleton04 instance;

    private Singleton04 () {}

    /**
     * 通过双重检查锁保证多线程的安全
     *
     *
     * @return
     */
    public static Singleton04 getInstance() {

        // 锁的颗粒度更细，如果实例已经创建完成，就不用抢锁了
        if (instance == null) {
            synchronized (Singleton04.class) {
                if (instance == null) {

                    /**
                     * 初始化对象的三部
                     * 1. 分配内存空间
                     * 2. 执行对象初始化
                     * 3. 将instance指向初始化的对象
                     *
                     * 当JVM执行指令重排的时候可能的执行步骤是1 3 2，可能出现获取到没有初始化好的对象的情况
                     *
                     */
                    instance = new Singleton04();
                }
            }
        }
        return instance;
    }
}
