package com.chason.leetcode.linkedlist;

public class Offer25_合并两个有序链表 {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode (int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode cur = new ListNode(-1);
        ListNode preHead = cur;                 // 记录头结点的位置 不然cur一旦变化这个位置就没了

        while (l1 != null && l2 != null) {      // 归并中的merge思想

            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {  // l2 == null
            cur.next = l1;
        }

        if (l2 != null) {
            cur.next = l2;
        }

        return preHead.next;
    }

}
