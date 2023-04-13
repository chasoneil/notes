package com.chason.leetcode.tree.traversal;

import com.chason.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _offer32_从上到下打印二叉树_ {

    public static int[] levelOrder(TreeNode root) {

        if (root == null) return new int[0];

        List<TreeNode> help = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();
            help.add(node);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        int[] res = new int[help.size()];
        for (int i=0; i<help.size(); i++) {
            res[i] = help.get(i).val;
        }

        return res;
    }

}
