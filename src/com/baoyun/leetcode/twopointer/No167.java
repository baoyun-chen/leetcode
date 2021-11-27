package com.baoyun.leetcode.twopointer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 *
 * Constraints:
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 */
public class No167 {

    public int[] twoSum(int[] numbers, int target) {
        int length = numbers.length;
        int index;
        if(length == 2 && numbers[0] + numbers[1] == target){
            return new int[]{0,1};
        }
        HashMap<Integer, Integer> mins = new HashMap();
        for(index = 0; index < length; index++){

            if(mins.containsKey(numbers[index])){
                return new int[]{mins.get(numbers[index]+1), index+1};
            }
            Integer min = target - numbers[index];
            mins.put(min, index);
        }
        return null;

    }

    public int[] twoSum2(int[] numbers, int target) {
        int pointer1 = 0;
        int pointer2 = numbers.length-1;

        do{
            if(numbers[pointer1] + numbers[pointer2]< target){
                pointer1++;
            }else if(numbers[pointer1] + numbers[pointer2] > target){
                pointer2--;
            }else{
                return new int[]{pointer1+1, pointer2+1};
            }

        }while(true);

    }

    @Test
    public void test(){
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        twoSum(nums, target);
    }
}