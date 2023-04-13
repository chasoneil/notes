package com.chason.leetcode.tree.traversal;

import com.chason.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的按层遍历使用队列这种数据结构
 */
public class _非递归_二叉树按层遍历_ {

    public static void main(String[] args) {

    }

    public static void list(TreeNode root) {

        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

    }

}
