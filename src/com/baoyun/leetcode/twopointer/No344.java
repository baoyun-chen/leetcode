package com.baoyun.leetcode.twopointer;
/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is a printable ascii character.
 *
 *
 * Follow up: Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 */
public class No344 {
    public void reverseString(char[] s) {
        int start = 0;
        int end = s.length-1;
        char temp;

        while (start < end){
            temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start ++;
            end--;
        }
    }


}
