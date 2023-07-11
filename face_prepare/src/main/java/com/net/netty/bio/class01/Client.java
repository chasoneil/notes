package com.net.netty.bio.class01;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * socket client
 *
 * always like user
 *
 */
public class Client {

    public static void main(String[] args) {

        Socket socket = null;

        ObjectOutputStream output = null;
        ObjectInputStream input = null;

        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",10001);

        try{
            socket = new Socket();
            socket.connect(addr);           // send socket to server try to connect

            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            output.writeUTF("Hello,Server. I am client~");
            output.flush();

            System.out.println(input.readUTF());
        } catch (IOException e) {
          e.printStackTrace();
        } finally {

            try {
                if (socket != null) {
                    socket.close();
                }

                if (output!=null) {
                    output.close();
                }

                if (input!=null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
