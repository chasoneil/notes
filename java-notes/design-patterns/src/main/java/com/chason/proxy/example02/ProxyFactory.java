package com.chason.proxy.example02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理的工厂
 */
public class ProxyFactory {

    private Object target;  // 需要被代理对象

    public ProxyFactory (Object target) {
        this.target = target;
    }

    // 获取代理类对象
    public Object getProxyInstance () {

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),    // 被代理类对象的类加载器
                target.getClass().getInterfaces(), // 被代理类的所有方法（接口）
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("before method do something...");
                        method.invoke(target, args);
                        System.out.println("after method do something...");

                        // 返回的是目标方法执行的结果，如果void就返回 null
                        return null;
                    }
                }
        );
    }

}
