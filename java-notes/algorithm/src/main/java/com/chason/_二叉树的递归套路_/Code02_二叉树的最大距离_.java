package com.chason._二叉树的递归套路_;

import com.chason.leetcode.tree.TreeNode;

public class Code02_二叉树的最大距离_ {

    public static int getMaxDistance (TreeNode root) {
        return process(root).maxDistance;
    }


    public static Info process (TreeNode node) {

        if (node ==null) return new Info(0, 0);

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = leftInfo.height >= rightInfo.height ? leftInfo.height + 1 : rightInfo.height + 1;

        // 整颗树的最大距离 除了考虑左右之外，如果穿过node 本身是不是也可能是最大距离啊
        int maxDistance = leftInfo.maxDistance >= rightInfo.maxDistance ? leftInfo.maxDistance : rightInfo.maxDistance;
        maxDistance = Math.max(leftInfo.height + rightInfo.height + 1, maxDistance);

        return new Info(maxDistance, height);
    }

    static class Info {

        int maxDistance;

        int height;

        public Info (int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }


}
