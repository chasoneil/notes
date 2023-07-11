package com.net.netty.bio.class02.v2.server;

import com.net.netty.bio.class02.pre.User;
import com.net.netty.bio.class02.pre.UserService;
import com.net.netty.bio.class02.pre.UserServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class V2Server {

    private static boolean running = true;

    public static void main(String[] args) throws  Exception{

        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("v2 server was started.");
        while (running){
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }

        serverSocket.close();
    }
    private static void  process(Socket socket) throws Exception {

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        DataInputStream dataInputStream = new DataInputStream(in);
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        int id = dataInputStream.readInt();

        UserService service =  new UserServiceImpl();
        User user = service.findUserByID(id);

        dataOutputStream.writeInt(user.getId());
        dataOutputStream.writeUTF(user.getName());
        dataOutputStream.flush();
    }



}
