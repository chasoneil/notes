package com.net.netty.nio.class01.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClientHandler implements Runnable {

    private String host;
    private int port;
    private volatile boolean started;
    private Selector selector;
    private SocketChannel socketChannel;


    public NioClientHandler(String ip, int port) {

        this.host = ip;
        this.port = port;
        try {

            this.selector = Selector.open();
            socketChannel = SocketChannel.open();

            /*如果为 true，则此通道将被置于阻塞模式；
             * 如果为 false，则此通道将被置于非阻塞模式
             * 缺省为true*/
            socketChannel.configureBlocking(false);
            started = true;

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public void stop(){
        started = false;
    }


    @Override
    public void run() {
        //连接服务器
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        /*循环遍历selector*/
        while(started){
            try {
                /*阻塞方法,当至少一个注册的事件发生的时候就会继续*/
                selector.select();
                /*获取当前有哪些事件可以使用*/
                Set<SelectionKey> keys = selector.selectedKeys();
                /*转换为迭代器*/
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while(it.hasNext()){
                    key = it.next();
                    it.remove();        // 处理完需要删除，否则下次还会处理
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if(key!=null){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*具体的事件处理方法*/
    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()) {

            SocketChannel sc =(SocketChannel)key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    socketChannel.register(selector,SelectionKey.OP_READ);
                } else {
                    System.exit(-1);
                }
            }

            /*处理读事件，也就是当前有数据可读*/
            if(key.isReadable()){

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                int readBytes = sc.read(buffer);
                if(readBytes>0){
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String result = new String(bytes,"UTF-8");

                    System.out.println("客户端收到消息："+result);
                }

                else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }

            }
        }
    }

    /*进行连接*/
    private void doConnect() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
        else{
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    /*写数据对外暴露的API*/
    public void sendMsg(String msg) throws IOException {
        doWrite(socketChannel, msg);
    }

    private void doWrite(SocketChannel sc,String request) throws IOException {

        byte[] bytes = request.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        sc.write(writeBuffer);
    }
}
