package com.chason.linkedlist;

/**
 * 从单向链表中删除指定的元素
 */
public class Code02_DeleteNode {

    public static class Node {
        private int value;
        public Node next;
        public Node (int value) {
            this.value = value;
        }
    }

    public static Node deleteNode(Node head, int target) {
        // 删除链表中指定接电的元素的重点就是 可能删除的是头节点，而且可能删除多个头节点
        while (head != null) {
            if (head.value != target) {
                break;
            }
            head = head.next;
        }

        // head come to value is not target
        while (head.next != null) {
            if (head.value == target) {
                head.next = head.next.next;
            }
        }
        return head;
    }

}
