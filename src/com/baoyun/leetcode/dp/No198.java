package com.baoyun.leetcode.dp;

/**
 *
 * https://leetcode.com/problems/house-robber/
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class No198 {


    /**
     *  We record the max money we can get up to each house.
     *
     *  The max money we can get for ith house is Max(maxMoney(i-1), monMoney(i-2)+money(i))
     *
     */
    public int rob(int[] nums) {
        int length = nums.length;
        int[] max = new int[length];
        for(int i = 0; i<length; i++){
            int maxOneHouseBefore = 0;
            int maxTwoHouseBefore = 0;
            if(i-1 >=0){
                maxOneHouseBefore = max[i-1];
            }
            if(i-2>=0){
                maxTwoHouseBefore = max[i-2];
            }
            max[i]=Math.max(maxOneHouseBefore, maxTwoHouseBefore + nums[i]);
        }
        return max[length-1];

    }

    public int rob2(int[] nums) {
        int length = nums.length;
        int maxOneHouseBefore = 0;
        int maxTwoHouseBefore = 0;
        int max = 0;
        for(int i = 0; i<length; i++){
            max = Math.max(maxOneHouseBefore, maxTwoHouseBefore + nums[i]);

            maxTwoHouseBefore = maxOneHouseBefore;
            maxOneHouseBefore = max;
        }
        return max;

    }
}
