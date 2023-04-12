package com.chason.leetcode.tree;

public class _二叉树的最大深度_ {

    // 使用递归的方法
    public static int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return leftDepth >= rightDepth ? ++leftDepth : ++rightDepth;
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
