package com.chason.linkedlist;

/**
 * 反转链表
 * 反转单向链表(Node)和双向链表(DoubleNode)
 */
public class Code01_ReverseList {


    /**
     * 单向链表
     */
    public static class Node {

        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 双向链表
     */
    public static class DoubleNode {
        private int value;
        private DoubleNode prev;
        private DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 反转单向链表
     * @param head
     * @return
     */
    public static Node reverseList(Node head) {

        // 采用双指针
        Node prev = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }


    public static DoubleNode reverseDoubleNode (DoubleNode head) {

        DoubleNode prev = null;
        DoubleNode next = null;

        while (head != null) {

            next = head.next;
            head.next = prev;
            head.prev = next;

            prev = head;
            head = next;
        }
        return prev;
    }

}
