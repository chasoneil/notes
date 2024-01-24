package com.chason.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

    @Test
    public void test01 () {
        System.out.println("test01");

        MethodAreaTest.doInit();

        MethodAreaTest.doTest();
    }

    /**
     * 使用断言判断 结果
     */
    @Test
    public void test02 () {

        int a = 10;
        int b = 20;
        int result = a + b;
        Assert.assertEquals(30, result);
    }


    /**
     * 在所有的测试方法开始执行前执行
     */
    @Before
    public void init () {
        System.out.println("start junit test.");
    }

    /**
     * 在所有的测试方法执行后执行
     */
    @After
    public void finish () {
        System.out.println("finish junit test.");
    }

}
