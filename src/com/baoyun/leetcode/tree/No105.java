package com.baoyun.leetcode.tree;

import org.junit.Test;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class No105 {


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,inorder,0,0,inorder.length-1);

    }

    public TreeNode build(int[] preorder, int[] inorder,
                          int preStartIndex, int inStartIndex, int inEndIndex) {

        if(inStartIndex >= inorder.length || inEndIndex < 0 || preStartIndex >= preorder.length){
            return null;
        }

        int rootValue = preorder[preStartIndex];
            for(int i=inStartIndex; i<=inEndIndex; i++){
                if(rootValue == inorder[i]){
                    int leftSize = (i-1)-inStartIndex +1;
                    int rightSize = inEndIndex - (i + 1) +1;
                    TreeNode left = leftSize == 0 ? null: build(preorder,inorder,
                            preStartIndex+1,
                            inStartIndex, i -1);
                    TreeNode right = rightSize ==0? null: build(preorder,inorder,
                            preStartIndex+leftSize+1,
                            i+1, inEndIndex);
                    return new TreeNode(rootValue,left, right);
                }
            }

        return null;
    }

    @Test
    public void test(){
        int [] preorder = new int[]{3,9,20,15,7};
        int [] inorder = new int[]{9,3,15,20,7};
        buildTree(preorder,inorder);
    }
}
