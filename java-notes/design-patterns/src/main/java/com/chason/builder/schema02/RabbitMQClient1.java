package com.chason.builder.schema02;

/**
 * 模拟和 rabbit mq 交互的客户端
 *
 * 当一个对象需要很多属性的时候，创建这个对象就会比较麻烦
 *
 * 本例中使用构造方法进行创建
 *
 */
public class RabbitMQClient1 {

    private String host = "127.0.0.1";

    private int port = 5672;

    private int mode = 1;

    private String exchange = "simple-exchange";

    private String queue = "test";

    private boolean isDurable = true;

    int timeout = 10000;

    public RabbitMQClient1 (String host, int port, int mode, String exchange, String queue, boolean isDurable) {
        this.host = host;
        this.port = port;
        this.mode = mode;
        this.exchange = exchange;
        this.queue = queue;
        this.isDurable = isDurable;

        // 需要进行参数的校验
        // 参数是否为空，某些情况下某些参数是否需要设置等等

        System.out.println("执行参数校验");

    }

    public void sendMessage() {
        System.out.println("发送消息...");
    }

    public static void main(String[] args) {

        /*
            使用这种方式容易出现错误，

            比如 port 和 mode 都是int 类型，就算传错了也不会报错，且不容易发现
            且传参数的时候容易漏传，非常麻烦
         */
        RabbitMQClient1 client1 = new RabbitMQClient1(
                "192.168.1.1", 5672, 1, "simple-exchange", "test", true
        );
        client1.sendMessage();

    }

}
