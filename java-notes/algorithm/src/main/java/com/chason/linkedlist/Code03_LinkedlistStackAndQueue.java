package com.chason.linkedlist;

/**
 * 使用双向链表实现 stack  and queue
 */
public class Code03_LinkedlistStackAndQueue {

    // 双向链表
    public static class Node {

        private int value;
        private Node prev;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleEndsStructure {

        public static Node head;
        public static Node tail;

        // 从头结点加入
        public static void addFromHead(int value) {
            Node node = new Node(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }

        }

        // 从尾节点加入
        public static void addFromTail(int value) {

            Node node = new Node(value);
            if (tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }

        // 从头部获取值
        public static int getFromHead() {

            if (head == null) {
                return -1;
            }

            int value = head.value;

            if (head == tail) { // 链表中只有一个节点
                head = tail = null;
            } else {            // 链表中至少有两个节点
                head = head.next;
                head.prev = null;
            }
            return value;
        }

        // 从尾部获取值
        public static int getFromTail() {

            if (tail == null) {
                return -1;
            }

            int value = tail.value;

            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            return value;

        }
    }


    public static class MyStack {

        public MyStack() {

        }

        public void add(int value) {
            DoubleEndsStructure.addFromHead(value);
        }

        public int pop() {
            return DoubleEndsStructure.getFromHead();
        }
    }


    public static class MyQueue{
        public MyQueue() {}

        public void add(int value) {
            DoubleEndsStructure.addFromHead(value);
        }


        public int push() {
            return DoubleEndsStructure.getFromTail();
        }
    }





}
