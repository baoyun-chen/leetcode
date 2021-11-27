package com.baoyun.leetcode.twopointer;

import org.junit.Test;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 */
public class No283 {

    public void moveZeroes(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return;
        }
        int pointer0 = -1;
        int non0Pointer;
        for (non0Pointer = 0; non0Pointer < length; non0Pointer++) {
            if (nums[non0Pointer] == 0) {
                pointer0 = pointer0 == -1 ? non0Pointer : pointer0;
            } else {
                if (pointer0 > -1) {
                    nums[pointer0] = nums[non0Pointer];
                    nums[non0Pointer] = 0;
                    pointer0++;
                }
            }
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes(nums);
    }
}
