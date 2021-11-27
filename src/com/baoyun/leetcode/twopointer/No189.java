package com.baoyun.leetcode.twopointer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 */
public class No189 {


    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;

        int index = length -k;

        revert(0,index-1,nums);
        revert(index,length-1, nums);
        revert(0,length-1,nums);


    }

    public void revert(int start, int end, int[]nums){
        int temp;
        while(start < end){
            temp = nums[start];
            nums[start]=nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void test(){
        int[] a = new int[]{-7,-3,2,3,4,11};
        rotate(a,3);
        Arrays.stream(a).forEach(System.out::println);
    }
}
