package com.chason.class01.classloader;

/**
 * 双重检查锁单例
 */
public class _04_DoubleCheckLock {

    /**
     * 为什么要 volatile?
     * 1. 线程1 开始执行初始化 通过双重检查锁开始进行 new instance 但是，他初始化没结束，只执行到了 给变量分配内存空间给初始值的过程，
     *    此时，instance 已经不是null了  正常初始化完 a = 100
     * 2. 线程2 开始 在第一个判断的时候 instance 不是空了，直接使用instance, 但是 instance 还没有初始化完成
     *    如果使用 a, 此时 a = 0
     *
     * 为什么使用了 volatile 就可以避免
     *
     * 出现上面问题的原因是，变量地址引用赋值的操作出现在了 变量给初始化值的前面
     * 如果我先赋值， 再让 a 的引用指向地址 这样就不会出现这个问题，其实Java中本身顺序也是这么写的
     * 但是，因为指令重排某些情况下可能会改变这个顺序，所以我们要在这里禁止指令重排
     *
     * 让 invokespecial 在 astore_1 之前
     *
     */
    private static volatile _04_DoubleCheckLock instance;

    private _04_DoubleCheckLock () {}

    public static _04_DoubleCheckLock getInstance() {

        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new _04_DoubleCheckLock();
                }
            }
        }
        return instance;
    }

}
