package com.chason.leetcode.linkedlist;


public class _2_两数相加_ {

    public static void main(String[] args) {

        ListNode list = addTwoNumbers(ListNode.createTestListNode1(), ListNode.createTestListNode2());

        System.out.println(list);


    }


    /*
        两个指针从前往后
        分别指向其中一个链表
        用一个变量来记录是否发生了进位 因为当前的这个条件，进位最多进1位
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode pre = new ListNode(-1);   // 头节点之前，用来标记当前节点
        ListNode cur = pre;

        if (l1 == null && l2 == null) {
            return null;
        }

        boolean isOver = false;

        while (l1 != null && l2 != null) {  // 第一种情况 两个都不是空

            int v = l1.val + l2.val;

            if (isOver) {
                ++v;
            }

            if (v >= 10) {
                isOver = true;  // 发生进位则 标记
            } else {
                isOver = false;
            }

            // 获取v 的有效个位数
            v %= 10;

            // 创建节点
            ListNode node = new ListNode(v);
            cur.next = node;
            cur = cur.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null && l2 == null) {
            int v = l1.val;
            if (isOver) {
                ++v;
            }
            if (v >= 10) {
                isOver = true;  // 发生进位则 标记
            } else {
                isOver = false;
            }

            v %= 10;
            ListNode node = new ListNode(v);
            cur.next = node;
            cur = cur.next;
            l1 = l1.next;
        }

        while (l1 == null && l2 != null) {
            int v = l2.val;
            if (isOver) {
                ++v;
            }
            if (v >= 10) {
                isOver = true;  // 发生进位则 标记
            } else {
                isOver = false;
            }

            v %= 10;
            ListNode node = new ListNode(v);
            cur.next = node;
            cur = cur.next;
            l2 = l2.next;
        }

        if (isOver) {
            ListNode node = new ListNode(1);
            cur.next = node;
        }

        return pre.next;
    }


}
