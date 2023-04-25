package com.chason.leetcode.tree.special;

/**
 * 特殊树结构找后继节点
 */
public class Code01_GetNextNode {


    public static Node getNextNode (Node node) {

        if (node == null) return null;

        if (node.right != null) {  // 有右树
            return getLeftMost(node.right);
        } else {        // 没有右树

            Node parent = node.parent;

            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }

            return parent;
        }

    }

    /**
     * 找树上的最左节点
     * @param node
     * @return
     */
    public static Node getLeftMost (Node node) {

        if (node == null) return node;

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}
