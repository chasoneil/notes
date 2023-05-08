package com.chason.linkedlist.skill;

import com.chason.class02.Node;

/**
 * 在链表上玩荷兰国旗问题
 * 给定一个单链表的头节点
 * 然后给定一个target 将小于target放在左边， 等于的放中间， 大于的放右边
 *
 *
 */
public class Code03_DutchFlagLinkedList {

    public static void main(String[] args) {

        Node node = partition(Node.createList1(), 3);
        System.out.println(node);

    }

    /**
     * 第一种思路很简单，我们将链表转化为数组， 然后类似快排我们使用数组玩partition
     * 最后玩好再把链表重新串起来
     * @param head
     * @return
     */
    public static Node partition(Node head, int target) {

        if (head == null || head.next == null) return head;

        Node cur = head;
        int size = 0;             // 计算链表节点长度
        while (cur != null) {
            cur = cur.next;
            size++;
        }

        Node[] help = new Node[size];

        cur = head;
        for (int i=0; i<help.length; i++) {
            Node next = cur.next;
            cur.next = null;
            help[i] = cur;
            cur = next;
        }

        // 放到数组中接下来就是经典的荷兰国旗问题了
        Node[] res = doPartition(help, target);

        for (int i=0; i<res.length; i++) {

            if (i == 0) {
                head = res[i];
                cur = head;
            } else {
                cur.next = res[i];
                cur = cur.next;
            }
        }

        return head;
    }

    public static Node[] doPartition(Node[] nodes, int target) {

        int less = -1;
        int more = nodes.length;

        for (int i=0; i<more; ) {

            if (nodes[i].val < target) {
                swap(nodes, less+1, i);
                less++;
                i++;
            } else if (nodes[i].val == target) {
                i++;
            } else {
                swap(nodes, i, more-1);
                more--;
            }
        }
        return nodes;

    }

    public static void swap (Node[] arr, int i, int j) {
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 使用指针的方法，空间时间复杂度更低
     * @return
     */
    public static Node pointer() {
        return null;
    }


}
