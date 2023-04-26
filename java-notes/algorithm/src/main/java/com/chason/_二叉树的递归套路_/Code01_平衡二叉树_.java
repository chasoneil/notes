package com.chason._二叉树的递归套路_;

import com.chason.leetcode.tree.TreeNode;

public class Code01_平衡二叉树_ {


    public static boolean isBalanced(TreeNode node) {

        return process(node).isBalanced;
    }


    public static Info process (TreeNode node) {

        if (node == null) return new Info(0, true);

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        // 根据上面的信息组合返回节点 node 的Info

        int height = leftInfo.height >= rightInfo.height ? leftInfo.height + 1 : rightInfo.height + 1;
        boolean isBalanced = true;

        int heightCut = Math.abs(leftInfo.height - rightInfo.height);
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || heightCut > 1) {
            isBalanced = false;
        }

        return new Info(height, isBalanced);
    }

    static class Info {

        public int height;

        public boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }

    }

}
