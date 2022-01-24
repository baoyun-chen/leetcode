package com.baoyun.leetcode;

import com.sun.tools.javac.util.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/permutations/
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
public class No46 {

    /**
     *  首先如果只有一个数字，就只有一种情况。 这个就是"终止点"
     *
     *  思路是，每一次先把第一位定下来，有 nums.length 种情况，
     *  然后剩下的数字再去算他的permute
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 1){
            List<Integer> newList = new ArrayList<>();
            newList.add(nums[0]);
            result.add(newList);
            return result;
        }
        int length = nums.length;
        for(int index = 0; index < length; index++ ){
            int setElement = nums[index];
            List<List<Integer>> partResult = permute(getRemainList(nums,index));
            partResult.forEach(e->e.add(0,setElement));
            result.addAll(partResult);
        }
        return result;
    }

    public int[] getRemainList(int[] nums, int removeIndex){
        int length = nums.length;
        int arrayIndex = 0;
        int[] result = new int[length-1];
        for(int index = 0; index< length; index++){
            if(index != removeIndex){
                result[arrayIndex] = nums[index];
                arrayIndex++;
            }
        }
        return result;
    }




    public List<List<Integer>> permute2(int[] nums) {
        boolean[] used = new boolean[nums.length];
        return permute2Helper(nums, used,0);
    }

    public List<List<Integer>> permute2Helper(int[] nums, boolean[] used, int usedLength) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if(length == 1 || usedLength == length -1){
            int unusedIndex;
            for( unusedIndex = 0; unusedIndex < length; unusedIndex++){
                if(!used[unusedIndex]){
                    break;
                }
            }
            List<Integer> subResult = new ArrayList<>();
            subResult.add(nums[unusedIndex]);
            result.add(subResult);
            return result;
        }

        for(int index = 0; index < length; index++){
            if(used[index]){
                continue;
            }
            used[index] = true;
            usedLength++;
            List<List<Integer>> subList = permute2Helper(nums, used, usedLength);
            int element = nums[index];
            subList.forEach(e->e.add(0, element));
            result.addAll(subList);
            used[index] = false;
            usedLength--;
        }
        return result;
    }



    public List<List<Integer>> permute3(int[] nums) {
        boolean[] used = new boolean[nums.length];
       //return permute3Helper(nums, used,0);

        return null;
    }


    @Test
    public void test(){
        permute2(new int[]{1,2,3});
    }
}
