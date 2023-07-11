package com.net.netty.bio.class02.v1.client;

import com.net.netty.bio.class02.pre.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class V1Client {

    public static User findUserByIDRemote(Integer id) throws Exception{

        Socket socket = new Socket("127.0.0.1",8888);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(out);

        dos.writeInt(id);

        socket.getOutputStream().write(out.toByteArray());
        socket.getOutputStream().flush();

        DataInputStream dis = new DataInputStream(socket.getInputStream());

        String name = dis.readUTF();
        User user = new User(id,name);

        dos.close();
        socket.close();
        return user;
    }


}
