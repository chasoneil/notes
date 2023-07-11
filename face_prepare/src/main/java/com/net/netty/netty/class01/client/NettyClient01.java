package com.net.netty.netty.class01.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyClient01 {

    private int port;
    private String host;

    public NettyClient01(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {

        /*线程组*/
        EventLoopGroup group = new NioEventLoopGroup();

        try{

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    /*指定使用NIO进行网络传输*/
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new NettyClientHandler01());
            /*连接到远程节点，阻塞直到连接完成*/
            ChannelFuture channelFuture = bootstrap.connect().sync();
            /*阻塞程序，直到Channel发生了关闭*/
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        new NettyClient01(9999,"127.0.0.1").start();
    }

}
