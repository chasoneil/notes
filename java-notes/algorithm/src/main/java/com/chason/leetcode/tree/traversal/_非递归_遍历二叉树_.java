package com.chason.leetcode.tree.traversal;

import com.chason.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * 使用非递归的方式遍历二叉树
 *
 *
 *
 */
public class _非递归_遍历二叉树_ {


    public static void main(String[] args) {

    }

    /*
    先序：先放入头节点 弹出即打印，如果有右节点，压入右，如果有左，压入左
     */
    public static void pre (TreeNode root) {

        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /*
    后序 ： 先序的顺序是 头左右 左右的顺序主要是通过 栈的左右节点先后压入的顺序决定的 那么我们只要改变顺序就能做到 头右左
    后序要求的顺序是： 左右头 和 我们上面的 头右左 刚好相反
    那么我们原本应该打印的时候不打印，直接放入一个栈中，然后出栈再打印，使用了两个栈，将顺序刚好颠倒，实现倒序打印
     */
    public static void pos(TreeNode root) {

        if (root == null) {
            return;
        }

        Stack<TreeNode> s1 = new Stack<>();  // 用来正常做 treeNode 的入栈出栈的
        Stack<TreeNode> s2 = new Stack<>();  // 辅助栈，目的是为了反转打印顺序

        s1.push(root);

        while (!s1.empty()) {
            TreeNode node = s1.pop();
            s2.push(node);
            if (node.left != null) {
                s1.push(node.left);
            }

            if (node.right != null) {
                s1.push(node.right);
            }
        }

        while (!s2.empty()) {       // 反序打印
            System.out.print(s2.pop().val + " ");
        }
    }

    /*
        逻辑1 -> 优先找到最左侧的节点， 但是在找的过程中，经历过的节点全部入栈，因为这是为了保证出栈的时候一定是最左侧节点先出
        当左侧已经结束了之后 执行逻辑2

        逻辑2 -> 左侧没了就开始弹出, 弹出之后，栈的上一个节点是头 ，然后去右节点继续执行逻辑1
     */
    public static void in(TreeNode node) {

        if (node == null) return;

        Stack<TreeNode> s = new Stack<>();

        while (!s.isEmpty() || node != null) {  // 为了左侧的入栈

            if (node != null) {
                s.push(node);
                node = node.left;
            } else {
                node = s.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }
}
