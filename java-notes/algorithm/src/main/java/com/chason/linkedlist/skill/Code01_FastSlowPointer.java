package com.chason.linkedlist.skill;

import com.chason.class02.Node;

/**
 * 快慢指针
 * * Q1: 给你链表头节点，奇数长度返回中点， 偶数长度返回上中点
 * * Q2: 给你链表头节点，奇数长度返回中点， 偶数长度返回下中点
 * * Q3: 给你链表头节点，奇数长度返回中点前一个， 偶数长度返回上中点
 * * Q4: 给你链表头节点，奇数长度返回中点前一个， 偶数长度返回下中点
 *
 */
public class Code01_FastSlowPointer {

    // Q1
    public static Node resForQ1 (Node head) {

        if (head == null || head.next == null || head.next.next == null) return head;

        Node slow = head.next;
        Node fast = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    // Q2
    public static Node resForQ2 (Node head) {

        if (head == null || head.next == null || head.next.next == null) return head;

        Node slow = head.next;
        Node fast = head.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Q3
    public static Node resForQ3 (Node head) {

        if (head == null || head.next == null || head.next.next == null) return head;

        Node slow = head;
        Node fast = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Q4
    public static Node resForQ4 (Node head) {

        if (head == null || head.next == null || head.next.next == null) return head;

        Node slow = head;
        Node fast = head.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

}
