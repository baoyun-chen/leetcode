package com.baoyun.leetcode.bit;

/**
 * https://leetcode.com/problems/single-number/
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 * Each element in the array appears twice except for one element which appears only once.
 */
public class No136 {

    /**
     *
     * https://leetcode.com/tag/bit-manipulation/discuss/1073221/All-about-Bitwise-Operations-Beginner-Intermediate
     * 1. a^0 = a
     * 2. a^a = 0
     * 3. x^y = y^x (Commutativity)
     * 4. (x^y)^z = x^(y^z) (Associativity)
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = nums[0];
        int length = nums.length;
        for(int i = 1; i< length; i++){
            single = single ^ nums[i];
        }
        return single;
    }
}
