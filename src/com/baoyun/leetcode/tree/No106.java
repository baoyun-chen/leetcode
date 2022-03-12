package com.baoyun.leetcode.tree;

import org.junit.Test;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 *
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 */
public class No106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder,postorder, postorder.length-1, 0, inorder.length-1);
    }

    public TreeNode build(int[] inorder, int[] postorder, int postIndex, int inStartIndex, int inEndIndex){
        if(postIndex < 0 || inStartIndex >= inorder.length || inEndIndex < 0){
            return null;
        }

        int rootValue = postorder[postIndex];

        for(int i = inStartIndex; i <= inEndIndex; i++){
            if(rootValue == inorder[i]){
                int leftSize = (i-1)- inStartIndex + 1;
                int rightSize = inEndIndex - (i+1) + 1;

                TreeNode right = rightSize == 0 ? null: build(inorder, postorder, postIndex-1, i+1, inEndIndex);
                TreeNode left = leftSize == 0 ? null: build(inorder, postorder, (postIndex -1) - rightSize, inStartIndex, i-1);

                return new TreeNode(rootValue, left,right);
            }
        }
        return null;
    }

    @Test
    public void test(){
        int [] postorder = new int[]{9,15,7,20,3};
        int [] inorder = new int[]{9,3,15,20,7};


        buildTree(inorder, postorder);
    }

}
