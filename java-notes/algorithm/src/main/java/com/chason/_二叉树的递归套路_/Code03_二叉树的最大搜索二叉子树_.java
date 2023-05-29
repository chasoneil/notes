package com.chason._二叉树的递归套路_;

import com.chason.leetcode.tree.TreeNode;

public class Code03_二叉树的最大搜索二叉子树_ {



    public static Info process (TreeNode head) {

        if (head == null) return null;

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isAllBST = false;

        int min = head.val;
        int max = head.val;

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }

        // 与节点 head 无关的时候

        int size = 0;
        if (leftInfo != null) {
            size = leftInfo.size;
        }

        if (rightInfo != null) {
            size = Math.max(size, rightInfo.size);
        }

        // 与head节点有关的时候

        if (
            // 1. 左树是搜索二叉树
            (leftInfo == null ? true : leftInfo.isAllBST)
            &&
            (rightInfo == null ? true : rightInfo.isAllBST)
            &&
            (leftInfo == null ? true : leftInfo.max < head.val)
            &&
            (rightInfo == null ? true : rightInfo.min > head.val)
        ) {
            size = (leftInfo == null ? 0 : leftInfo.size) + (rightInfo == null ? 0 : rightInfo.size + 1);
            isAllBST = true;
        }

        return new Info(size, isAllBST, max, min);
    }

    static class Info {

        public int size;    // 整颗树的最大二叉搜索树的大小

        public boolean isAllBST;

        public int max; // 左树最大值

        public int min; // 右树最小值

        public Info (int size, boolean isAllBST, int max, int min) {
            this.size = size;
            this.isAllBST = isAllBST;
            this.max = max;
            this.min = min;
        }
    }

}
