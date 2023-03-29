package com.chason.linkedlist;

/**
 * 使用数组实现一个栈和队列结构
 * 假设，我们的数组是给定长度的，当使用超出我们给定的长度的时候，报错
 */
public class Code04_ArrayStackAndQueue {


    public class MyStack {

        private int[] arr;

        private int index = 0;

        public MyStack (int capacity) {
            arr = new int[capacity];
        }

        public void push(int value) {
            if (index >= arr.length) {
                System.err.println("stack out of flow.");
            }
            arr[index] = value;
            index++;
        }

        public int pop() {
            if (index == 0) {
                return -1;
            }

            return 0;
        }

    }






}
