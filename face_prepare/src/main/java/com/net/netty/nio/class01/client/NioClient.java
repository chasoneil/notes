package com.net.netty.nio.class01.client;

import java.util.Scanner;

public class NioClient {


    private static NioClientHandler nioClientHandler;

    public static void start() {

        if (nioClientHandler != null) {
            nioClientHandler.stop();
        }

        nioClientHandler = new NioClientHandler("127.0.0.1",10001);

        new Thread(nioClientHandler,"Server").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        nioClientHandler.sendMsg(msg);
        return true;
    }


    public static void main(String[] args) throws Exception {
        start();
        Scanner scanner = new Scanner(System.in);
        while(NioClient.sendMsg(scanner.next()));

    }


}
