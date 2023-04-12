package com.chason.leetcode.tree;

/**
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class _验证二叉搜索树_ {


    public static boolean isValidBST (TreeNode root) {



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
