package com.chason.leetcode.linkedlist;

public class ListNode {

    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }


    public static ListNode createTestListNode1 () {

        ListNode head = new ListNode(9);
        head.next = new ListNode(9);
        head.next.next = new ListNode(9);
        head.next.next.next = new ListNode(9);
        head.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next = new ListNode(9);

        return head;
    }

    public static ListNode createTestListNode2 () {

        ListNode head = new ListNode(9);
        head.next = new ListNode(9);
        head.next.next = new ListNode(9);
        head.next.next.next = new ListNode(9);
        return head;
    }
}
