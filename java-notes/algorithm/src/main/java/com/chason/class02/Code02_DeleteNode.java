package com.chason.class02;

/**
 * 从单向链表中删除指定的元素
 */
public class Code02_DeleteNode {

    public static void main(String[] args) {


        Node head = deleteNode(Node.createList1(), 2);
        System.out.println(head);
    }

    public static Node deleteNode(Node head, int target) {

        // 删除链表中指定接电的元素的重点就是 可能删除的是头节点，而且可能删除多个头节点

        // 找到值不是要删除的头节点，这个一定是新链表的头
        while (head != null) {
            if (head.val != target) {
                break;
            }
            head = head.next;
        }

        // 这里出现的问题是，有可能存在多个要删除的节点连在一起，要通过双指针全部跳过去
        Node prev = head;       // 相当于上一个不等于的位置
        Node cur = head;        // 相当于当前遍历到的节点

        while (cur != null) {

            if (cur.val == target) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
