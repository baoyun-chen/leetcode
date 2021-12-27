package com.baoyun.leetcode.twopointer;

import com.baoyun.leetcode.common.ListNode;

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 100].
 * 1 <= Node.val <= 100
 */

public class No876 {
    public ListNode middleNode(ListNode head) {
        if(head.next == null){
            return head;
        }
        int length = 1;
        ListNode node = head;
        while(node.next != null){
            length++;
            node = node.next;
        }
        int middle = Math.floorDiv(length, 2) + 1;
        int index = 1;
        node = head;
        while(index < middle){
            node = node.next;
            index++;
        }

        return node;

    }
    public ListNode middleNode2(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while(fast !=null && fast.next != null){
            fast = fast.next.next;
            slow =  slow.next;
        }
        return slow;

    }

}
