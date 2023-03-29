package com.chason.linkedlist;

/**
 * 使用数组实现一个栈和队列结构
 * 假设，我们的数组是给定长度的，当使用超出我们给定的长度的时候，报错
 */
public class Code04_ArrayStackAndQueue {


     static class MyStack {

        private int[] arr;
        private int index = 0;

        public MyStack (int capacity) {
            arr = new int[capacity];
        }

        public void push(int value) {
            if (index >= arr.length) {
                System.err.println("stackAndQueue out of flow.");
            }
            arr[index] = value;
            index++;
        }

        public int pop() {
            if (index == 0) {    // 里面压根没有数据
                return -1;
            }

            int value = arr[index-1];
            index--;
            return value;
        }
    }


    /**
     * 使用 arr 实现队列需要借助 ring buffer (环状结构)
     */
    static class MyQueue {

        private int[] arr;

        private int pushIndex = 0;
        private int popIndex = 0;

        private int size = 0;

        private int capacity;

        public MyQueue (int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
        }

        public void push (int value) {
            if (size == capacity) {
                throw new RuntimeException("Range out of Queue");
            }
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
            size++;
        }

        /**
         * 从队列中 弹出一个值
         */
        public int poll() {

            if (size == 0) {
                throw new RuntimeException("Queue has no element");
            }

            int value = arr[popIndex];
            popIndex = nextIndex(popIndex);
            size--;
            return value;
        }

        /**
         * 数组循环利用的关键
         * 指针到了数组尾部会从头开始继续 实现ringArray
         * @param index
         * @return
         */
        public int nextIndex(int index) {
            return index == capacity - 1 ? 0 : index + 1;
        }
    }



    public static void main(String[] args) {

        MyQueue queue = new MyQueue(5);

        new Thread(() -> {  // push

            for (int i=0; i<100; i++) {
                queue.push(i);
                System.out.println("Push :" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }

        }).start();

        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {

        }

        new Thread(() -> {

            for (int i=0; i<100; i++) {
                queue.poll();
                System.out.println("Poll :" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        }).start();
    }

}
