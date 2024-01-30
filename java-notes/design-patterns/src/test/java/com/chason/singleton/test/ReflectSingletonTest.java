package com.chason.singleton.test;

import com.chason.singleton.Singleton01;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * 使用反射破坏单例的方法
 */
public class ReflectSingletonTest {

    /**
     * 使用反射将单例模式进行破坏
     */
    @Test
    public void test01 () {

        Class<Singleton01> clazz = Singleton01.class;

        try {
            Constructor<Singleton01> constructor = clazz.getDeclaredConstructor();

            constructor.setAccessible(true);  // 私有构造方法需要设置才能调用

            Singleton01 instance01 = constructor.newInstance();
            Singleton01 instance02 = constructor.newInstance();

            Assert.assertEquals(instance01, instance02);  // false
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
