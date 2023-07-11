package com.net.netty.bio.class02.v3.server;

import com.net.netty.bio.class02.pre.UserServiceImpl;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class V3Server {

    private static boolean running = true;

    public static void main(String[] args) throws  Exception {

        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("v3 server was started.");

        while (running){
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }
        serverSocket.close();
    }

    private static void  process(Socket socket) throws Exception{

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        //我自己定义的协议（className|methodName|parametersTypes|args）
        String clazzName = ois.readUTF();
        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[])ois.readObject();
        Object[] args =(Object[])ois.readObject();

        //反射拿到class
        Class clazz = Class.forName(clazzName);
        if(clazz.isInterface()){
            if(clazzName.equals("com.net.netty.bio.class02.pre.UserService")){
                clazz = UserServiceImpl.class;
            }
            //这里可以使用反射机制拿到所有接口对应的实现类
        }

        Method method = clazz.getMethod(methodName,parameterTypes);

        Object object = method.invoke(clazz.newInstance(),args);

        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(object);
        oos.flush();
    }



}
