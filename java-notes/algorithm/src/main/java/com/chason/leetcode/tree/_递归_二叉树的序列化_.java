package com.chason.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 中序和后序是没法通过递归进行反序列化的
 */
public class _递归_二叉树的序列化_ {


    public static void main(String[] args) {

        // --- 序列化 ---
        TreeNode root = TreeNode.createTestTree3();
        Queue<String> queue1 = preSerialize(root);
        System.out.println(queue1);

        Queue<String> queue2 = inSerialize(root);
        System.out.println(queue2);

        Queue<String> queue3 = posSerialize(root);
        System.out.println(queue3);


        // --- 反序列化 ---

        buildByQueue(queue1);



    }

    /**
     * 先序的方式进行序列化
     * 顺序是 头左右
     * @return
     */
    public static Queue<String> preSerialize(TreeNode head) {
        Queue<String> queue = new LinkedList<>();
        pres(head, queue);
        return queue;

    }

    static void pres(TreeNode head, Queue<String> queue) {

        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.val));
            pres(head.left, queue);
            pres(head.right, queue);
        }

    }

    public static Queue<String> inSerialize(TreeNode head) {

        Queue<String> queue = new LinkedList<>();
        ins(head, queue);
        return queue;

    }

    public static Queue<String> posSerialize(TreeNode head) {

        Queue<String> queue = new LinkedList<>();
        poss(head, queue);
        return queue;

    }

    /**
     * 左头右
     * @param head
     * @param queue
     */
    static void ins(TreeNode head, Queue<String> queue) {

        if (head == null) {
            queue.add(null);
        } else {
            ins(head.left, queue);
            queue.add(String.valueOf(head.val));
            ins(head.right, queue);
        }

    }

    // 左右头
    static void poss(TreeNode head, Queue<String> queue) {

        if (head == null) {
            queue.add(null);
        } else {
            poss(head.left, queue);
            poss(head.right, queue);
            queue.add(String.valueOf(head.val));
        }

    }

    static TreeNode buildByQueue(Queue<String> queue) {

        if (queue == null || queue.size() == 0) return null;
        TreeNode root = preb(queue);
        return root;
    }

    // 头左右
    static TreeNode preb(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = preb(queue);
        node.right = preb(queue);
        return node;
    }
}
