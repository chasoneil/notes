package com.chason.singleton.test;

import com.chason.singleton.Singleton07;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * enum singleton test
 */
public class EnumSingletonTest {

    /**
     * is enum singleton?
     */
    @Test
    public void test01 () {

        Singleton07 instance01 = Singleton07.getInstance();
        Singleton07 instance02 = Singleton07.getInstance();

        // we can't do that (create object by new in enum)
        // Singleton07 instance03 = new Singleton07();

        Assert.assertEquals(instance01, instance02);
    }

    /**
     * test reflect can destroy the singleton?
     */
    @Test
    public void test02 () {

        Class<Singleton07> clazz = Singleton07.class;

        try {
            Constructor<Singleton07> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            /*
              throw NoSuchMethodException on the method newInstance()
              we can't create new object with constructor reflect when singleton was enum

              so, It's safe for singleton!
             */

            Singleton07 instance01 = constructor.newInstance();
            Singleton07 instance02 = constructor.newInstance();

            Assert.assertEquals(instance01, instance02);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * test serialize destroy singleton ?
     */
    @Test
    public void test03 () throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile.obj"));
        oos.writeObject(Singleton07.getInstance());

        File file = new File("tempFile.obj");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Singleton07 instance01 = (Singleton07) ois.readObject();
        Singleton07 instance02 = Singleton07.getInstance();

        Assert.assertEquals(instance01, instance02);        // true
    }


}
