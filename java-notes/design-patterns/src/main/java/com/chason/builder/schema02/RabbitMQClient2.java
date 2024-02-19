package com.chason.builder.schema02;

/**
 * 为了解决第一个客户端的例子，将构造方法的行为改成set
 */
public class RabbitMQClient2 {

    private String host = "127.0.0.1";

    private int port = 5672;

    private int mode = 1;

    private String exchange = "simple-exchange";

    private String queue = "test";

    private boolean isDurable = true;

    int timeout = 10000;

    public RabbitMQClient2 () {}

    public void setHost(String host) {
        this.host = host;
    }

    public void setDurable(boolean durable) {
        isDurable = durable;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void sendMessage() {
        System.out.println("发送消息...");
    }

    public static void main(String[] args) {

        RabbitMQClient2 client2 = new RabbitMQClient2();
        client2.setHost("192.168.1.1");
        client2.setPort(3306);
        client2.setMode(2);
        client2.setExchange("simple1-exchange");
        client2.setQueue("test1");
        client2.setDurable(false);

        // 需要进行参数的校验
        /*
            这个方法解决了上面第一个客户端带来的问题，可以一个个的设置参数
            但是，将参数的校验的逻辑放到了客户端中，这个是不合理的

            如果不放到客户端，那么校验的逻辑就要放到set 方法中，这种情况下set参数的先后顺序会影响结果

            并且这种情况破坏了一个类的参数的完整性，等于参数可以随时进行更改
         */

        client2.sendMessage();
    }
}
