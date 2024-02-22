package com.chason.prototype;

import com.chason.prototype.deep.DeepObject;
import com.chason.prototype.shallow.ShallowObject;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PrototypeTest {

    /**
     * 测试浅克隆对象的情况
     */
    @Test
    public void test01 () throws Exception {

        ShallowObject s1 = new ShallowObject();
        ShallowObject s2 = s1.clone();

        System.out.println(s1 == s2);
    }

    /**
     * 测试浅克隆的对象地址引用
     * @throws Exception
     */
    @Test
    public void test02 () throws Exception {

        ShallowObject s1 = new ShallowObject();
        Person p = new Person("张三");
        s1.setPerson(p);
        s1.getPerson().show();

        ShallowObject s2 = s1.clone();
        s2.getPerson().setName("李四");
        s1.getPerson().show();

    }

    /**
     * 使用序列化的方式实现深克隆
     * @throws Exception
     */
    @Test
    public void test03 () throws Exception {

        Person p = new Person("张三");
        DeepObject d1 = new DeepObject(p);

        d1.getPerson().show();

        // 通过深克隆创建第二个对象

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("deep.obj"));
        oos.writeObject(d1);

        ObjectInputStream ois  = new ObjectInputStream(new FileInputStream("deep.obj"));
        DeepObject d2 = (DeepObject) ois.readObject();
        d2.getPerson().setName("李四");

        d1.getPerson().show();
    }

}
