package com.chason.singleton.test;

import com.chason.singleton.Singleton01;
import com.chason.singleton.Singleton02;
import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {

    /**
     * 单例 饿汉 是不是单例
     */
    @Test
    public void test01 () {

        Singleton01 instance01 = Singleton01.getInstance();
        Singleton01 instance02 = Singleton01.getInstance();
        Singleton01 instance03 = Singleton01.getInstance();
        Assert.assertEquals(instance01.hashCode(), instance02.hashCode());
        Assert.assertEquals(instance01.hashCode(), instance03.hashCode());
    }

    /**
     * 单例 懒汉  是不是单例
     */
    @Test
    public void test02 () {
        Singleton02 instance01 = Singleton02.getInstance();
        Singleton02 instance02 = Singleton02.getInstance();
        Singleton02 instance03 = Singleton02.getInstance();
        Assert.assertEquals(instance01.hashCode(), instance02.hashCode());
        Assert.assertEquals(instance01.hashCode(), instance03.hashCode());
    }

}
