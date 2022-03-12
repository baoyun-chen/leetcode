package com.baoyun.leetcode.tree;

import org.junit.Test;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
 *
 * If there exist multiple answers, you can return any of them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 * Example 2:
 *
 * Input: preorder = [1], postorder = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * All the values of preorder are unique.
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * All the values of postorder are unique.
 * It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
 */
public class No889 {

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            return build(preorder, postorder, 0, 0, postorder.length-1-1);
    }

    public TreeNode build(int[] preorder, int[] postorder, int rootIndex, int postStartIndex, int postEndIndex){

        if(rootIndex >= preorder.length-1 || postStartIndex >= postorder.length || postEndIndex < 0){
            return new TreeNode(preorder[rootIndex]);
        }

        int rootLeftValue = preorder[rootIndex +1];

        for(int i = postStartIndex; i <= postEndIndex; i++){
            if(rootLeftValue == postorder[i]){
                int leftSize = i -postStartIndex +1;
                int rightSize = postEndIndex - (i+1) +1;

                TreeNode left = leftSize ==0 ? null: build(preorder,postorder,rootIndex+1, postStartIndex, i-1);

                TreeNode right = rightSize ==0 ? null: build(preorder, postorder, rootIndex + leftSize +1, i+1, postEndIndex-1);

                return new TreeNode(preorder[rootIndex], left, right);
            }
        }
        return new TreeNode(preorder[rootIndex]);

    }

    @Test
    public void test(){
        int[] preorder = new int[]{1,2,4,5,3,6,7};
        int[] postorder = new int[]{4,5,2,6,7,3,1};
        constructFromPrePost(preorder, postorder);

    }

}
