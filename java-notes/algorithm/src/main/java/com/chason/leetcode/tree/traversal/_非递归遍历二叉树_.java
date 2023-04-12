package com.chason.leetcode.tree.traversal;

import java.util.Stack;

/**
 * 使用非递归的方式遍历二叉树
 *
 *  先序：先放入头节点 弹出即打印，如果有右节点，压入右，如果有左，压入左
 *
 */
public class _非递归遍历二叉树_ {


    public static void main(String[] args) {
        TreeNode n7 = new TreeNode(7, null, null);
        TreeNode n6 = new TreeNode(5, null, null);
        TreeNode n5 = new TreeNode(6, null, null);
        TreeNode n4 = new TreeNode(2, null, null);
        TreeNode n3 = new TreeNode(4, n6, n7);
        TreeNode n2 = new TreeNode(1, n4, n5);
        TreeNode root = new TreeNode(3, n2, n3);

        pre(root);
    }

    // pre
    public static void pre (TreeNode root) {

        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

    }


    static class TreeNode  {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode () {}

        TreeNode (int val) {
            this.val = val;
        }

        TreeNode (int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
