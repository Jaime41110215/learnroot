package com.mini.leetcode.树;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghongchao
 * @time 2020/5/18
 */
public class _110_平衡二叉树 {

    Map<TreeNode,Integer> map = new HashMap<>();

    public boolean isBalanced(TreeNode root) {
        if(!isBalanced(root.left)) return false;
        if(!isBalanced(root.right)) return false;

        int lh = height(root.left);
        int rh = height(root.right);

        if(Math.abs(lh - rh) > 1){
            return false;
        }else {
            return true;
        }
    }

    public int height(TreeNode node){
        if(map.containsKey(node)){
            return map.get(node);
        }else {
            if(node == null){
                map.put(node,0);
                return 0;
            }
            int height = Math.max(height(node.left),height(node.right)) + 1;
            map.put(node,height);
            return height;
        }
    }

    private TreeNode head;

}
