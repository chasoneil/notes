package com.chason.class02;

/**
 * 反转链表
 * 反转单向链表(Node)和双向链表(DoubleNode)
 */
public class Code01_ReverseList {

    /**
     * 反转单向链表
     * @param head
     * @return
     */
    public static Node reverseList(Node head) {

        // 采用双指针 和 虚拟头节点
        Node prev = null;
        Node next = null;   // next 指针用于记录要更改节点的下一个节点

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

            // 双向链表要调整两条指针
            head.next = prev;
            head.prev = next;
            prev = head;

            head = next;
        }
        return prev;
    }

}
