package com.chason.linkedlist.skill;

import com.chason.linkedlist.Node;

import java.util.Stack;

/**
 *  判断一个单链表是不是回文结构
 *
 *  所谓的回文结构就是  可以有个对称轴的轴对称结构  abcba
 */
public class Code02_Huiwen {

    public static void main(String[] args) {

        System.out.println(isHuiWen2(Node.createList()));
    }


    /**
     * 使用栈，遍历链表压栈
     * 出栈的时候是反向的，依次出栈的时候和原链表进行对比
     *
     * 时间复杂度 O(N)
     * 额外空间复杂度 O(N)
     * @param head
     * @return
     */
    public static boolean isHuiWen(Node head) {

        if (head == null) return true;

        Stack<Node> s = new Stack<>();
        Node node = head;
        while (node != null) {
            s.push(node);
            node = node.next;
        }

        while (!s.empty()) {
            node = s.pop();
            if (node.val != head.val) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    /**
     * 升级版
     * 使用快慢指针 找到中点  偶数就是中点靠上一个的位置
     * 然后把 剩下的东西放到栈里面去
     * 依次出栈和原链表进行比较
     *
     * 时间复杂度 O(N)
     * 额外空间复杂度 O(N/2)
     * @return
     */
    public static boolean isHuiWen2 (Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head.next;
        Node fast = head.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow 已经是 目标位置

        Stack<Node> s = new Stack<>();

        while (slow != null) {
            s.push(slow);
            slow = slow.next;
        }

        while (!s.isEmpty()) {
            if (s.pop().val != head.val) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

}
