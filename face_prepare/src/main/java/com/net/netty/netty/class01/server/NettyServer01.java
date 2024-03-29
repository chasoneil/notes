package com.net.netty.netty.class01.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer01 {

    private int port;

    public NettyServer01 (int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {

        int port = 9999;
        NettyServer01 echoServer = new NettyServer01(port);
        System.out.println("服务器即将启动");
        echoServer.start();
        System.out.println("服务器关闭");
    }

    public void start() throws InterruptedException {
        final NettyServerHandler01 serverHandler = new NettyServerHandler01();
        /*线程组*/
        // EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup group = new NioEventLoopGroup(1);
        try {
            /*服务端启动必须*/
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)/*将线程组传入*/
                    .channel(NioServerSocketChannel.class)/*指定使用NIO进行网络传输*/
                    .localAddress(new InetSocketAddress(port))
                    /*服务端每接收到一个连接请求，就会新启一个socket通信，也就是channel，
                    所以下面这段代码的作用就是为这个子channel增加handle*/
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            /*添加到该子channel的pipeline的尾部*/
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture f = serverBootstrap.bind().sync();/*异步绑定到服务器，sync()会阻塞直到完成*/
            f.channel().closeFuture().sync();/*阻塞直到服务器的channel关闭*/

        } finally {
            group.shutdownGracefully().sync();/*优雅关闭线程组*/
        }

    }


}
