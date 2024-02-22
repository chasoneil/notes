package com.chason.proxy.example03;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用CGLIB 进行动态代理
 */
public class CGUserProxyFactory implements MethodInterceptor {

    /**
     * 其实等同于动态代理的 invoke
     * @param o  目标对象
     * @param method 目标对象的方法包装
     * @param args   参数
     * @param methodProxy 代理类对象的方法包装
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("[" + sdf.format(new Date()) + "] " + method.getName() + " 被调用.");

        return methodProxy.invokeSuper(o, args);
    }

    public Object getProxy (Object target) {

        Enhancer enhancer = new Enhancer();
        /*
            CGLIB是通过生成目标对象的子类，然后对子类进行代理的操作，所以这里是代理对象（子类）的父类
            其实就是 代理目标本身
         */
        enhancer.setSuperclass(target.getClass());

        /*
            设置增强器的回调，其实就是子类要调用的方法
            本质上 谁重写了 interceptor 方法就要传入谁
            我们这里是本类自己实现，所以传入自己
         */
        enhancer.setCallback(this);
        // 最终就是通过增强器去产生代理对象
        return enhancer.create();
    }

}
