package com.chason.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class _非递归_二叉树按层序列化_ {

    public static void main(String[] args) {

        TreeNode root = TreeNode.createTestTree3();
        System.out.println(seriaByLevel(root));

    }


    /*
        二叉树按层序列化 重点是元素加入队列的时候进行序列化
        基本思想还是按层 依次弹出元素
        所以当然有两个 队列， 一个队列是用来存储我们序列化的结果，另一个队列是我们常规进行弹出操作的队列
     */
    public static Queue<String> seriaByLevel (TreeNode head) {

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> res = new LinkedList<>();

        if (head == null) {
            res.add(null);
        } else {

            res.add(String.valueOf(head.val));      // 先进行序列化，再进行普通的弹入弹出
            queue.add(head);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                    res.add(String.valueOf(node.left.val));
                } else {
                    res.add(null);
                }

                if (node.right != null) {
                    queue.add(node.right);
                    res.add(String.valueOf(node.right.val));
                } else {
                    res.add(null);
                }
            }

        }
        return res;
    }

    public static TreeNode buildByLevel (Queue<String> queue) {

        if (queue == null || queue.size() == 0) {
            return null;
        }

        TreeNode head = createNode(queue.poll());       // 先把头节点创建出来保存
        Queue<TreeNode> help = new LinkedList<>();      // 辅助队列
        help.add(head);         // 保存头节点

        TreeNode node = null;

        while (!help.isEmpty()) {

            node = help.poll();
            // 不管，就算是 null 也要给他创建节点
            node.left = createNode(queue.poll());
            node.right = createNode(queue.poll());

            if (node.left != null) {
                help.add(node.left);
            }

            if (node.right != null) {
                help.add(node.right);
            }
        }

        return head;

    }

    private static TreeNode createNode (String val) {
        if (val == null) {
            return null;
        }
        return new TreeNode(Integer.parseInt(val));
    }

}
