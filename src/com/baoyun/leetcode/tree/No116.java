package com.baoyun.leetcode.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 *
 *
 * Follow-up:
 *
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class No116 {

    // using queue
    public Node connect1(Node root) {
        if(root == null){
            return root;
        }
       Queue<Node> parent = new LinkedList<>();
       root.next = null;
       parent.offer(root);

       while(!parent.isEmpty()){
           Queue<Node> children = new LinkedList<>();
           Node current = parent.poll();
           while(current != null){
               Node next = parent.poll();
               if(current.left != null){
                   children.offer(current.left);
                   children.offer(current.right);
                   current.left.next = current.right;
                   current.right.next = next == null ? null : next.left;
               }
               current = next;
           }
           parent = children;
       }
       return root;

    }

    // fix extra space
    // keys are 1. we can use curr.next
    //           2. record the level start
    public Node connect2(Node root) {
        if(root == null){
            return root;
        }
        root.next = null;

        Node levelStart = root;
        while(levelStart != null){
            Node curr = levelStart;
            while(curr != null){
                if(curr.left != null){
                    curr.left.next = curr.right;
                    curr.right.next = curr.next == null ? null: curr.next.left;
                }
                curr = curr.next;
            }
            levelStart = levelStart.left;
        }


        return root;

    }

    public Node connect3(Node root) {
        if(root != null && root.left != null && root.right != null){
            root.left.next = root.right;
            root.right.next = root.next == null? null: root.next.left;
            connect3(root.left);
            connect3(root.right);
        }

        return root;
    }


    @Test
    public void test(){

        Node node1 = new Node(-1);
        Node node2 = new Node(0);
        Node node3 = new Node(1);
        Node node4 = new Node(2);
        Node node5 = new Node(3);
        Node node6 = new Node(4);
        Node node7 = new Node(5);
        Node node8 = new Node(6);
        Node node9 = new Node(7);
        Node node10 = new Node(8);
        Node node11 = new Node(9);
        Node node12 = new Node(10);
        Node node13 = new Node(11);
        Node node14 = new Node(12);
        Node node15 = new Node(13);

        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        node4.left=node8;
        node4.right=node9;
        node5.left=node10;
        node5.right=node11;
        node6.left=node12;
        node6.right=node13;
        node7.left=node14;
        node7.right=node15;
        connect1(node1);
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
