package com.baoyun.leetcode.bit;

/**
 * https://leetcode.com/problems/power-of-two/
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 *
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 *
 * Input: n = 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 *
 * Input: n = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * -231 <= n <= 231 - 1
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 */
public class No231 {

    /**
     *  if a number is power of two, for bit format, it will a "1" followed by 0s
     *  say n = 1000,  n-1 will be  0111
     *  n&(n-1) will be 0
     *
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if(n<=0){
            return false;
        }
        int m = n-1;
        int r = n&m;
        if(r!=0)
            return false;
        return true;
    }
}
