package com.chason.singleton.test;

import com.chason.singleton.Singleton06;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * 序列化破坏单例模式
 *
 */
public class SerializeSingletonTest {

    /**
     * 序列化的方式破坏了单例
     * @throws Exception
     */
    @Test
    public void test01 () throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile.obj"));
        oos.writeObject(Singleton06.getInstance());

        File file = new File("tempFile.obj");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Singleton06 instance01 = (Singleton06) ois.readObject();

        Singleton06 instance02 = Singleton06.getInstance();

        Assert.assertEquals(instance01, instance02);
    }


}
