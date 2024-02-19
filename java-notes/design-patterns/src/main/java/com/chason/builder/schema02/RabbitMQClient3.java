package com.chason.builder.schema02;

/**
 * 使用建造者模式解决上述两个类中的问题
 *
 * 1. 目标类的构造方法要多传入一个Builder对象
 * 2. builder类位于目标类的内部 static修饰
 * 3. builder类提供set方法，返回值是builder对象本身
 * 4. builder类提供一个build方法，创建目标对象
 * 5. 目标类构造方法私有化
 */
public class RabbitMQClient3 {

    private RabbitMQClient3 (Builder builder) {

    }

    public static class Builder {

        private String host = "127.0.0.1";

        private int port = 5672;

        private int mode = 1;

        private String exchange = "simple-exchange";

        private String queue = "test";

        private boolean isDurable = true;

        int timeout = 10000;

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setDurable(boolean durable) {
            isDurable = durable;
            return this;
        }

        public Builder setExchange(String exchange) {
            this.exchange = exchange;
            return this;
        }

        public Builder setMode(int mode) {
            this.mode = mode;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setQueue(String queue) {
            this.queue = queue;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }


        public RabbitMQClient3 build () {

            // 加入校验的逻辑 在这个时候，所有的参数都已经设置完成了

            return new RabbitMQClient3(this);
        }
    }

    public void sendMessage() {
        System.out.println("发送消息...");
    }


    public static void main(String[] args) {

        RabbitMQClient3 client3 = new RabbitMQClient3.Builder().setHost("192.168.1.1")
                .setPort(1234).setMode(2).setExchange("my-exchange")
                .setQueue("my-queue").setDurable(false).build();

        client3.sendMessage();
        System.out.println(client3);
    }

}
