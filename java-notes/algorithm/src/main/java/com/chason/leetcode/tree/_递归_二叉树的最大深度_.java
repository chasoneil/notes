package com.chason.leetcode.tree;

public class _递归_二叉树的最大深度_ {

    // 使用递归的方法
    public static int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return leftDepth >= rightDepth ? ++leftDepth : ++rightDepth;
    }
}
