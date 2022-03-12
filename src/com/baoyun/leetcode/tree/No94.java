package com.baoyun.leetcode.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 */
public class No94 {
    public List<Integer> inorderTraversal(TreeNode root) {


        List<Integer> list = new ArrayList();
        visit(list, root);
        return list;
    }

    public void visit(List<Integer> list, TreeNode root){
        if(root == null){
            return;
        }
        visit(list, root.left);
        list.add(root.val);
        visit(list, root.right);

    }
}
