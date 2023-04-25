package com.chason.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class _验证二叉搜索树_ {


    /*
        这题的难点在于
              5
           4     6
               3   7
        这其实不是一个二叉搜索数， 不满足的元素是3， 3是右树， 右树的每个节点都要比跟节点大
        但是 3 < 5 所以不满足需求

        其实根据二叉搜索数的定义，如果采用中序遍历，一定是有序的, 那么只要我们采用中序遍历，
        看看是不是每个元素比前面一个小，比后面一个大
     */
    public static boolean isValidBST (TreeNode head) {

        if (head == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> lists = new ArrayList<>();

        while (!stack.empty() || head != null) {

            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                lists.add(head);
                head = head.right;
            }
        }

        for (int i=0; i<lists.size(); i++) {
            if (i-1 >= 0 && lists.get(i).val <= lists.get(i-1).val) {
                return false;
            }

            if (i+1 < lists.size() && lists.get(i).val >= lists.get(i+1).val) {
                return false;
            }
        }

        return true;
    }



    public static boolean isValidBST1 (TreeNode root) {

        if (root == null) {
            return true;
        }

        // 先序遍历  头左右
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        int max = -1;
        int min = -1;
        while (!stack.empty()) {
            TreeNode node = stack.pop();

            // 左子树 右节点 有要求
            if (node.left != null) {
                max = max > node.val ? max : node.val;
                if (node.left.val >= node.val) {
                    return false;
                }

                if (node.right.val >= max) {
                    return false;
                }
            }

            // 右子树 左节点有要求
            if (node.right != null) {
                min = min < node.val ? min : node.val;
                if (node.right.val >= node.val) {
                    return false;
                }

                if (node.left.val <= min) {
                    return false;
                }
            }

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return true;
    }
}
