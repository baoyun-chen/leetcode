package com.baoyun.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class No144 {
    public List<Integer> preorderTraversal(TreeNode root) {


        List<Integer> list = new ArrayList();
        visit(list, root);
        return list;
    }

    public void visit(List<Integer> list, TreeNode root){
        if(root == null){
            return;
        }
        list.add(root.val);
        visit(list, root.left);
        visit(list, root.right);

    }


}
