package com.chason.leetcode.tree.traversal;

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
public class _递归遍历二叉树_ {

    public static void main(String[] args) {

        TreeNode n7 = new TreeNode(7, null, null);
        TreeNode n6 = new TreeNode(5, null, null);
        TreeNode n5 = new TreeNode(6, null, null);
        TreeNode n4 = new TreeNode(2, null, null);
        TreeNode n3 = new TreeNode(4, n6, n7);
        TreeNode n2 = new TreeNode(1, n4, n5);
        TreeNode root = new TreeNode(3, n2, n3);

        // pre(root);

        in(root);

        // pos(root);

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
