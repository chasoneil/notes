package com.chason.proxy;

import com.chason.proxy.example01.IUserDao;
import com.chason.proxy.example01.UserDaoImpl;
import com.chason.proxy.example01.UserProxy;
import com.chason.proxy.example02.ProxyFactory;
import com.chason.proxy.example03.CGUserProxyFactory;
import com.chason.proxy.example03.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class ProxyTest {

    /**
     * 静态代理的测试
     */
    @Test
    public void staticProxyTest () {

        UserProxy userProxy = new UserProxy(new UserDaoImpl());
        userProxy.save();
    }

    /**
     * JDK动态代理的测试
     */
    @Test
    public void dynamicProxyTest () {

        ProxyFactory factory = new ProxyFactory(new UserDaoImpl());
        IUserDao proxy = (IUserDao) factory.getProxyInstance();

        proxy.save();

    }

    /**
     * CGLIB 进行动态代理
     */
    @Test
    public void cglibProxyTest () {

        CGUserProxyFactory factory = new CGUserProxyFactory();
        UserServiceImpl proxy = (UserServiceImpl) factory.getProxy(new UserServiceImpl());

        proxy.selectUser();
    }


}
