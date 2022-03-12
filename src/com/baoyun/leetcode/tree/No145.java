package com.baoyun.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 */
public class No145 {
    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList();
        visit(list, root);
        return list;
    }

    public void visit(List<Integer> list, TreeNode root){
        if(root == null){
            return;
        }
        visit(list, root.left);
        visit(list, root.right);
        list.add(root.val);
    }


}
