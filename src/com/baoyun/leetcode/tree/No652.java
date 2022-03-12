package com.baoyun.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 * Example 2:
 *
 *
 * Input: root = [2,1,1]
 * Output: [[1]]
 * Example 3:
 *
 *
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 *
 *
 * Constraints:
 *
 * The number of the nodes in the tree will be in the range [1, 10^4]
 * -200 <= Node.val <= 200
 */
public class No652 {
    // use class variable will be quicker than passing variable to the function
    private HashMap<String, Integer> map = new HashMap<>();
    private List<TreeNode> list = new ArrayList<>();


    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        visit(root);
        return list;

    }

    public String visit(TreeNode root){
        if( root == null){
            return "#";
        }
        String left = visit(root.left);
        String right = visit(root.right);

        // cannot use  left+root+right
        //   0    and    0         will have the same key: #0#0#
        //  /             \
        // 0                0
        String key = left + "," + right + "," + root.val;

        if(map.containsKey(key)){
            if(map.get(key).equals(1)){
                list.add(root);
                map.put(key,2);
            }
        }else{
            map.put(key,1);
        }

        return key;
    }
}
