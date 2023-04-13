package com.chason.leetcode.tree.traversal;

import com.chason.leetcode.tree.TreeNode;

/**
 * 使用递归的方式
 * 先序 中序 后序遍历二叉树
 *
 *      3
 *   1     4
 * 2  6  5   7
 *
 * 先序： 3 1 2 6 4 5 7
 * 中序： 2 1 6 3 5 4 7
 * 后序： 2 6 1 5 7 4 3
 */
public class _递归_遍历二叉树_ {

    public static void main(String[] args) {


    }


    // 先序
    public static void pre(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        pre(root.left);
        pre(root.right);
    }

    // 中序
    public static void in(TreeNode root) {
        if (root == null) {
            return;
        }
        in(root.left);
        System.out.print(root.val + " ");
        in(root.right);
    }

    public static void pos(TreeNode root) {
        if (root == null) {
            return;
        }
        pos(root.left);
        pos(root.right);
        System.out.print(root.val + " ");
    }


}
