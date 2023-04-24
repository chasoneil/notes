package com.chason.review;

public class _MergeSort_review_ {

    public static ListNode sortList(ListNode head) {

        int[] arr = linkedListToArray(head);
        sort(arr);
        return arrayToLinkedList(arr);

    }

    public static void main(String[] args) {

        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(-1);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(4);

        sortList(head);
    }

    public static int[] linkedListToArray(ListNode head) {
        ListNode node = head;
        int count = 0;

        while (node != null) {
            count++;
            node = node.next;
        }

        int[] arr = new int[count];
        int index = 0;
        node = head;
        while (node != null) {
            arr[index] = node.val;
            index++;
            node = node.next;
        }
        return arr;
    }

    public static ListNode arrayToLinkedList(int[] arr) {

        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode pre = new ListNode(-1);
        ListNode cur = pre;
        for (int i=0; i<arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            cur.next = node;
            cur = cur.next;
        }

        return pre.next;
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }

        int mid  = L + (R - L) / 2;
        process(arr, L, mid);
        process(arr, mid+1, R);
        merge(arr, L, mid, R);
    }

    // 对一个数组进行合并
    public static void merge (int[] arr, int L, int M, int R) {

        int[] help = new int[R - L + 1];

        int index = 0;
        int p1 = L;
        int p2 = M+1;

        while (p1 <=M && p2 <= R) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[index++] = arr[p1++];
        }

        while (p2 <= R) {
            help[index++] = arr[p2++];
        }

        for (int i=0; i<help.length; i++) {
            arr[L+i] = help[i];
        }
    }
}
