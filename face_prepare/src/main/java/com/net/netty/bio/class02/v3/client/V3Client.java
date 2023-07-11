package com.net.netty.bio.class02.v3.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class V3Client {

    public static Object getStub(final Class clazz) throws Exception{

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Socket socket = new Socket("127.0.0.1", 8888);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                String className = clazz.getName();
                String methodName = method.getName();
                Class[] parametersTypes = method.getParameterTypes();

                // 传递class到服务器 ， 我自己定义的协议（className|methodName|parametersTypes|args）
                oos.writeUTF(className);
                oos.writeUTF(methodName);
                oos.writeObject(parametersTypes);
                oos.writeObject(args);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object o = ois.readObject();
                oos.close();
                socket.close();
                return o ;
            }
        };

        Object o = Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},handler);
        return o;
    }

}
