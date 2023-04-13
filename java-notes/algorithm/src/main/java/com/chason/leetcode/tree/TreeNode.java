package com.chason.leetcode.tree;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode () {}

    public TreeNode (int val) {
        this.val = val;
    }

    public TreeNode (int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    /**
     *      1
     *   2     3
     * 4   5  6   7
     *   8      9
     *
     * @return
     */
    public static TreeNode createTestTree1 () {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.right.left = new TreeNode(8);
        root.right.left.right = new TreeNode(9);

        return root;
    }
}
