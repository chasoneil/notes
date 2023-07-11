package com.net.netty.nio.class01.server;

public class NioServer {

    private static NioServerHandler nioServerHandler;

    public static void start() {

        if(nioServerHandler !=null) nioServerHandler.stop();
        nioServerHandler = new NioServerHandler(10001);
        new Thread(nioServerHandler, "Server").start();
    }


    public static void main(String[] args){
        start();
    }

}
