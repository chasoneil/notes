package com.chason.leetcode.linkedlist;

import com.chason.review.ListNode;

public class _Offer26_重排链表_ {

    public static void reorderList(ListNode head) {

        ListNode node = head;

        ListNode reverseHead = reverse(node);

        node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }

        ListNode next = head.next;
        ListNode next2 = reverseHead.next;
        for (int i=0; i<count; i++) {

            head.next = reverseHead;
            reverseHead.next = next;

        }


    }

    public static ListNode reverse (ListNode head) {
        // todo
        return null;
    }


}
