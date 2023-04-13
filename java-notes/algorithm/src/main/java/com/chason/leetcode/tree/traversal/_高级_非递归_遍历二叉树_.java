package com.chason.leetcode.tree.traversal;

import com.chason.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * 使用非递归的方法后序遍历二叉树
 *
 * 要求：只能使用一个栈
 *
 */
public class _高级_非递归_遍历二叉树_ {

    public static void main(String[] args) {

    }


    public static void pos(TreeNode h) {

        if (h == null) {
            return;
        }

        Stack<TreeNode> s = new Stack<>();
        s.push(h);

        /*
         当前栈顶的节点  在栈顶表示即将以这个节点进行处理
         怎么处理，要么开始处理他的左子树，要么处理的他的右子树，要么处理它本身

         后序遍历的处理方式是 左右头 怎么确定我处理到哪里了呢
         所以我们复用 h 去标记之前一次处理过的位置
         如果之前是左，那么意味着我应该处理右边了， 如果之前是右，那么意味着我左右都处理完了，该处理我自己了
         如果既不是左又不是右，那么说明左边还没处理呢，继续将左压栈
         */
        TreeNode cur = null;

        while (!s.empty()) {
            cur = s.peek();
            if (cur.left != null && h != cur.left && h != cur.right) { // 第一个分支， h 和 cur 没关系，直接压栈
                s.push(cur.left);
            } else if (cur.right != null && h != cur.right) {  // 左子树处理完了
                s.push(cur.right);
            } else {    // 左右都处理完了
                System.out.print(s.pop().val + " ");
                h = cur;        // 让h 记录我上次处理的位置
            }
        }


    }
}
