package com.mini.leetcode.树;

/**
 * https://leetcode-cn.com/problems/binode-lcci/
 */
public class _面试题17_12_BiNode {

    TreeNode temp;

    public TreeNode convertBiNode(TreeNode root) {
        temp = new TreeNode(Integer.MIN_VALUE);
        TreeNode result = temp;
        inOrderTraversal(root);
        return result.right;
    }

    private void inOrderTraversal(TreeNode root){
        if(root == null) return;
        inOrderTraversal(root.left);
        temp.right = root;
        root.left = null;
        temp = temp.right;
        inOrderTraversal(root.right);
    }

}
