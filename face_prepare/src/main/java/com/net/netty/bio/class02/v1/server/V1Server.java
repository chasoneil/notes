package com.net.netty.bio.class02.v1.server;

import com.net.netty.bio.class02.pre.User;
import com.net.netty.bio.class02.pre.UserService;
import com.net.netty.bio.class02.pre.UserServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class V1Server {

    private static boolean running = true;

    public static void main(String[] args) throws  Exception {

        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("v1 server started.");
        while (running){
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }
        serverSocket.close();
    }

    //网络通讯的代码  业务代码 耦合在一起。
    private static void  process(Socket socket) throws Exception{

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        DataInputStream dataInputStream = new DataInputStream(in);
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        int id = dataInputStream.readInt();     // client send id to server

        UserService service =  new UserServiceImpl(); // local server do search
        User user = service.findUserByID(id);
        dataOutputStream.writeUTF(user.getName());  // return name to client

        dataOutputStream.flush();
    }

}
