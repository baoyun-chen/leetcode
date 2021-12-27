package com.baoyun.leetcode;

import com.baoyun.leetcode.common.ListNode;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
public class No21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode next = head;
        while(list1 != null && list2 !=null){
            if(list1.val <= list2.val){
                next.next = list1;
                list1 = list1.next;
            }else{
                next.next = list2;
                list2 = list2.next;
            }
            next = next.next;
        }

        if(list1 == null){
            next.next = list2;
        }
        if(list2 == null){
            next.next = list1;
        }
        return head.next;
    }


    public ListNode mergeTwoLists2(ListNode list1, ListNode list2){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }

        if(list1.val <= list2.val){
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        }else{
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }


}


