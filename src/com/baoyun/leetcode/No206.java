package com.baoyun.leetcode;

import com.baoyun.leetcode.common.ListNode;
import org.junit.Test;

/**
 *
 * https://leetcode.com/problems/reverse-linked-list/
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 *
 *
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class No206 {

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode();

        while(head!=null ){
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }


    //recursive
    public ListNode reverseList2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        // move to find the last node, which will return by above 2 line
        ListNode newHead = reverseList2(head.next);

        // 1->2->3->4->5
        // say here head = 4,  4.next = 5,
        // therefore this code will become 4<=>5, 4.next=5 , 5.next =4
        head.next.next = head;
        head.next = null;   // and now we want to remove 4->5, so make 4.next = null

        return newHead;

        // after 4 is processed, we return back to 3 in line 66.
        // now the list is 1->2->3->4<-5
        //3.next = 4, 4.next = 3
        // remove 3->4,   will become 1->2->3<-4<-5
        // and still 5 as the new head
    }


    @Test
    public void test(){
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,new ListNode(5)))));
        reverseList2(head);
    }

}
