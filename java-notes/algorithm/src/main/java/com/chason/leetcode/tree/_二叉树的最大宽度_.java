package com.chason.leetcode.tree;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 求二叉树的最大宽度 最大宽度肯定是跟层数相关的
 */
public class _二叉树的最大宽度_ {

    public static void main(String[] args) {

        TreeNode root = TreeNode.createTestTree1();

        System.out.println("最大宽度：" + maxWidthWithMap(root));


    }


    /**
     * 使用 Map 参与统计
     * 这个方法可以解决层数的统计 以及最大宽度的统计
     * @return
     */
    public static int maxWidthWithMap(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Map<TreeNode, Integer> nodeLevel = new HashMap<>(); // key : node, value : key 这个node 在第几层
        int curLevelNodes = 0;  // 当前层的节点数量
        int level = 1;          // 当前到第几层了

        int max = 0;            // 全局最大宽度

        nodeLevel.put(root, 1);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        /*
            我们约定 当一个节点弹出的时候，我们再把它加入到宽度中
         */
        while (!queue.isEmpty()) {

            TreeNode tmp = queue.poll();
            int curNodeLevel = nodeLevel.get(tmp);  // 弹出的节点在哪一层

            if (tmp.left != null) {
                nodeLevel.put(tmp.left, curNodeLevel + 1);      // 你的子节点一定是当前节点层数的下一层
                queue.add(tmp.left);
            }

            if (tmp.right != null) {
                nodeLevel.put(tmp.right, curNodeLevel + 1);
                queue.add(tmp.right);
            }

            /*
               上面的代码是宽度优先遍历 如果到上面结束，其实就是按照层依次弹出打印
               接下来我们要 进行深度统计和层节点统计了
             */

            if (level == curNodeLevel) {      // 说明我还没到下一层
                curLevelNodes++;                // 弹出一个就记录一下数量
            } else {
                // 说明我们开始走新的一层了
                max = Math.max(max, curLevelNodes);  // 将当前最大宽度保留
                level++;
                curLevelNodes = 1;              // 因为已经弹出了一个了，我们要把这个算进去
            }
        }

        /*
            我们上面的逻辑是，每次到下一层了，开始结算上一层的统计数据，那么势必最后一层的数据统计没有结算
         */
        max = Math.max(max, curLevelNodes);

        System.out.println("层数是：" + level);
        return max;

    }
}
