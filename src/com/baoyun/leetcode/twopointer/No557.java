package com.baoyun.leetcode.twopointer;

import org.junit.Test;

/**
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Example 2:
 *
 * Input: s = "God Ding"
 * Output: "doG gniD"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * s contains printable ASCII characters.
 * s does not contain any leading or trailing spaces.
 * There is at least one word in s.
 * All the words in s are separated by a single space.
 */
public class No557 {

    public String reverseWords(String s) {
        char[] sArray =  s.toCharArray();
        int start = 0;
        int end = 0;
        while(end < s.length()){
            if(sArray[end] != ' '){
                end++;
            }else{
                reverseString(sArray, start, end-1);
                end++;
                start = end;
            }
        }
        reverseString(sArray, start, end-1);
        return String.valueOf(sArray);
    }

    public void reverseString(char[] s, int start, int end) {
        char temp;
        while (start < end){
            temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start ++;
            end--;
        }
    }

    @Test
    public void test() {
        String a = "Let's take LeetCode contest";

        System.out.println(reverseWords(a));
    }
}
