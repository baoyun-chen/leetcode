package com.baoyun.leetcode.dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 */
public class No70 {

    /**
     *  First when there are 1 or 2 stairs, we know how many ways
     *  When there are 3 stairs, we can
     *      1. choose to step 1 stair
     *      2. choose to step 2 stairs
     *  So the ways for 3 stairs are  1 + 2
     *
     *  For more stairs, we can see like
     *     1. if I choose to step 1 stair at first, how many ways are there to step for the rest stairs
     *     2. if I choose to step 2 stairs at first, how many ways are there to step for the rest stairs
     *  So the ways for part1 + part2
     *  for the remaining call the same function
     *
     *
     *  We don't want to reprogram ways for number of stairs again and again
     *  So we create an array to record the result
     *  if we can find the result in the record,
     *  we don't need to call the function instead we just need to get from the record
     *  so we create the record array
     *
     *
     */
    public int climbStairs(int n) {
      int[] record = new int[n+1];
      if(n >=1){
          record[1] = 1;
      }
      if(n>=2){
          record[2] = 2 ;
      }
      return getWays(n,record);

    }

    public int getWays(int n, int[] record){
        if(n>2){
            int part1 = record[n-1] > 0 ? record[n-1] : getWays(n-1, record);
            int part2 = record[n-2] > 0 ? record[n-2] : getWays(n-2, record);
            record[n] = part1 + part2;
        }
        return record[n];
    }


    /**
     *  The other way of thinking:
     *  We can step 1 stair or 2 stairs at once
     *  So for n stairs, we can think at the last step, we can step 1 stair or step 2 stairs
     *  so the combination would be ways of (n-1) + ways of (n-2)
     *
     *  Think of n as the third number,
     *  ways of current number = ways of two number before + ways of one number before
     *
     *
     */
    public int climbStairs2(int n) {
        int waysOfCurrentNumber = 0;
        int waysOfTwoNumberBefore = 1;
        int waysOfOneNumberBefore = 2;
        if(n <= 1){
            return 1;
        }

        if(n == 2){
            return 2;
        }

        for(int number = 3; number <=n; number++){
            waysOfCurrentNumber = waysOfTwoNumberBefore +waysOfOneNumberBefore; // we computed for third number

            // get ready for the next number, so we reset the ways of the previous two number
            // think of moving the points one step to the right
            // two number before for n +1 would be n-1 (one number before for n)
            // one number before for n +1 would be n ( currentNumber)
            waysOfTwoNumberBefore = waysOfOneNumberBefore;
            waysOfOneNumberBefore = waysOfCurrentNumber;
        }

        return waysOfCurrentNumber;

    }

    @Test
    public void test(){
        climbStairs(4);
    }
}
