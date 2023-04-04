package com.chason.leetcode;

public class Offer25_合并两个有序链表 {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode (int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // 双指针，分别从两个链表的头节点开始，当一个链表结束了，那么就直接将另外一个链表链接过来

        ListNode next;

        while (l1 != null && l2 != null) {  // 两个链表都还有数据
            if (l1.val > l2.val) {

            }
        }

        return null;
    }

}
