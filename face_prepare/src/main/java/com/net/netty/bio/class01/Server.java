package com.net.netty.bio.class01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket server
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket();     // socketServer was used to accept socket
        serverSocket.bind(new InetSocketAddress(10001));

        System.out.println("Server started.");

        while(true){
            new Thread(new ServerTask(serverSocket.accept())).start();
        }
    }

    private static class ServerTask implements Runnable{

        private Socket socket = null;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            ObjectInputStream inputStream = null;
            ObjectOutputStream outputStream = null;
            try {
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream = new ObjectOutputStream(socket.getOutputStream());

                String message = inputStream.readUTF(); // accept message from client
                System.out.println(Thread.currentThread().getName() + "-Accept client message:" + message);

                outputStream.writeUTF("Hello,Client.I've received your message.");
                outputStream.flush();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
