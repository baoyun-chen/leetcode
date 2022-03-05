package com.baoyun.leetcode.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 *
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 *
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 * Example 2:
 *
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * Example 3:
 *
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 *
 * Constraints:
 *
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 */
public class No1335 {


    /**
     *
     *
     * Say that we have n jobs to finish in d days
     * 1. for the first day we can finish 1 job to the n-(d-1) th jobs ( set the last job be finished as lastJobIn1 job)
     * 2. for the second day we can finish lastJobIn1+1 job to the n - (d-2) th jobs ( set the last job be finished as lastJobIn2 job)
     * 3. for the dth day, we can finish lastJobIn{d-1} +1 job to the n th job
     *
     *
     *  use the formula: we need to find the state and function
     *  from above, can see the states are `day` and the job we start from that day `start_job`
     *  dp(day,startJob)
     *
     *  and we can see the login the function dp:
     *      total_diff = hardest job of the day + total_diff of the rest days
     *
     *      dp(1,0) will be the result to the question
     *      dp(1,0) = hardest_job_in_day1 + dp(2, start_job_day_2);
     *
     *      --> actually there is a hidden state, job we finish on that day end_job
     *      start_job_day = end_jod_previous_day + 1
     *
     *      as we say at first, we can at most finish job up to n-(d-1)
     *      so loop appear:
     *
     *      for( endjob = startJob; endjob <= n-(d-day))
     *       dp(day,startJob) = hardest + dp(day+1, endJob+1)
     *
     *      and we get the min from it.
     *
     *
     *
     */
    public int minDifficulty(int[] jobDifficulty, int d) {

        int totalJobs = jobDifficulty.length;

        if(totalJobs < d){
            return -1;
        }
        int[][] memo = new int[totalJobs][d+1];
        int[] hardestRemain = new int[totalJobs];
        int hardest = Integer.MIN_VALUE;
        for(int i = totalJobs-1; i>= 0; i--){
            hardest = Math.max(hardest, jobDifficulty[i]);
            hardestRemain[i] = hardest;
            for(int k = 0; k <= d; k++){
                memo[i][k] = -1;
            }
        }

        return minDiff(0,1, jobDifficulty, d, memo, hardestRemain);
    }

    public int minDiff(int i, int day, int[] jobDifficulty, int d, int[][] memo, int[] hardestRemain){
        int totalJobs = jobDifficulty.length;
        int maxIndex = totalJobs - (d -day) - 1;
        int hardest = Integer.MIN_VALUE;
        int minDiff = Integer.MAX_VALUE;

        if(d == day){
            return hardestRemain[i];

        }

        if(memo[i][day] == -1){
            for(int k = i; k <= maxIndex; k++){
                hardest = Math.max(hardest, jobDifficulty[k]);
                int minDiffFromNextday = 0;

                minDiffFromNextday = minDiff(k+1, day+1, jobDifficulty,d, memo, hardestRemain);
                minDiff = Math.min(minDiff, hardest + minDiffFromNextday);

            }
            memo[i][day] = minDiff;
        }
        return memo[i][day];
    }


    /**
     *
     *  we can think of this way:
     *  if we finish all the jobs in one day:
     *      1-n
     *  if we finish all the jobs in two days:
     *     1 + 2-n
     *     12 + 3-n
     *     123 + 4-n
     *     ....
     *  if we finish all the jobs in three days:
     *     1 + 2 + 3-n
     *     1 + 23 + 4-n
     *     1 + 234 + 5-n
     *     ...
     *     1+ 2 - n-1 + n
     *
     *     12 + 3- n-1 + n
     *     123 + 4- n-1 + n
     *   --------------------------------------------
     *
     *
     *     this logic is very difficult to get
     *
     *     just need to know it is thinking like we get the total_diff from finish all the jobs in 1 day to n days
     *
     *     int [] diffMap is recording the min total_diff to finish job from i to length-1 in certain number of days
     *     so, for finish jobs in 1 day:  diffMap = (max(abcd), max(bcd), max(cd), max(d))
     *     for 2 days: diffMap[0] = min diff of (a +bcd, ab+ cd, abc+d)
     *      first part is the max diff amount the jobs,
     *      second part we can find in the previous day
     *
     *      ...
     *
     *
     *
     *
     *
     */
    public int minDifficulty2(int[] jobDifficulty, int day) {

        int totalJobs = jobDifficulty.length;

        if (totalJobs < day)
            return -1;

        int [] diffMap = new int[totalJobs];
        Arrays.fill(diffMap, Integer.MAX_VALUE);

        for(int dayToFinJobs = 1; dayToFinJobs <= day; dayToFinJobs++){
            int jobNeedsRemain = dayToFinJobs -1;
            int maxIndexUpTo = (totalJobs-1) - jobNeedsRemain;

            for(int firstJobInDay1Index = 0; firstJobInDay1Index <= maxIndexUpTo; firstJobInDay1Index++){
                int maxTaskDiffInFirstDay = Integer.MIN_VALUE;
                diffMap[firstJobInDay1Index] = Integer.MAX_VALUE;  // need to reset as day pass
                for(int j = firstJobInDay1Index; j <= maxIndexUpTo; j++){
                    maxTaskDiffInFirstDay = Math.max(maxTaskDiffInFirstDay, jobDifficulty[j]);
                    int totalDiffInRemainDay = j+1 == totalJobs ? 0: diffMap[j+1];
                    int totalDiff = totalDiffInRemainDay == Integer.MAX_VALUE ? Integer.MAX_VALUE: maxTaskDiffInFirstDay + totalDiffInRemainDay;
                    diffMap[firstJobInDay1Index] = Math.min(diffMap[firstJobInDay1Index], totalDiff);
                }
            }
        }
        return diffMap[0];
    }


    public int minDifficulty3(int[] jobDifficulty, int d) {
        int totalJobs = jobDifficulty.length;
        if (totalJobs < d)
            return -1;

        int[][] map = new int[d+1][totalJobs];
        for(int i = 0; i<= d; i++){
            Arrays.fill(map[i],Integer.MAX_VALUE);
        }
        int maxJobsOneDay = totalJobs - d +1;
        
        int maxDiff = Integer.MIN_VALUE;
        for(int k = totalJobs-1; k > totalJobs-1-maxJobsOneDay; k--){
            maxDiff = Math.max(maxDiff, jobDifficulty[k]);
            map[d][k] = maxDiff;
        }
        
        
        for(int day = d-1; day >=1; day--){
            int maxEndIndex = (totalJobs-1)-(d -day);
            int maxStartIndex = maxEndIndex- maxJobsOneDay +1;
            for(int startIndex = maxStartIndex; startIndex <= maxEndIndex; startIndex++ ){
                int thisMaxDiff = Integer.MIN_VALUE;
                
                for(int endIndex = startIndex; endIndex <= maxEndIndex; endIndex++){
                    thisMaxDiff = Math.max(thisMaxDiff, jobDifficulty[endIndex]);
                    int followDaysDiffTotal = map[day+1][endIndex+1];
                    map[day][startIndex] = Math.min( map[day][startIndex], thisMaxDiff+followDaysDiffTotal);
                }
            }
        }

        return map[1][0];

    }



    public int minDifficulty4(int[] jobDifficulty, int d) {
        final int totalJobs = jobDifficulty.length;
        if(totalJobs < d) return -1;
        int[][] dp = new int[d+1][totalJobs];

        dp[1][0] = jobDifficulty[0];
        for(int j = 1; j < totalJobs; j++){
            dp[1][j] = Math.max(jobDifficulty[j], dp[1][j - 1]);
        }

        for(int day = 2; day < d; d++){
            for(int len = day; len < totalJobs; len++){
                int localMax = jobDifficulty[len];
                dp[day][len] = Integer.MAX_VALUE;
                for(int schedule = len; schedule >= day; --schedule){
                    localMax = Math.max(localMax, jobDifficulty[schedule]);
                    dp[day][len] = Math.min(dp[day][len], dp[day - 1][schedule - 1] + localMax);
                }
            }
        }

        return dp[d - 1][totalJobs - 1];
    }


    /**
     *
     *
     *
     *   total_diff = hardest job of the day + total_diff of the rest days
     *   so our base case is the last day
     *
     *   because we need to finish at least one job per day:
     *   so the maximum job number we can finish in one day is `maxJobsOneDay` = totalJobs -d +1
     *
     *   we set up a diff_map : ith element in the map stores the min total_diff start from the ith job
     *
     *   we treat as we do the job with the revers order
     *
     *   in the last day:
     *      we have to finish the last job
     *      we at least start last job,
     *      at most can start from `maxStartJobIndex` =totalJobs-1-maxJobsOneDay` th job
     *
     *   so we can easily get the base value of our diff_map[last_job] back to diff_map[maxStartJobIndex]
     *
     *   after the last day, the logic is sightly different:
     *      because for last day, we have the finish the last job.
     *      but for the other days, we don't have the job that we have to finish
     *      but we have job range
     *   for the day before the last day:
     *      we have left at least one job for last day
     *      so the `maxEndJobIndex` = (totalJobs-1) - 1
     *      at most can start from `maxStartJobIndex` =maxEndJobIndex-maxJobsOneDay+1 th job
     *      the job range is `maxStartJobIndex` to `maxEndJobIndex`
     *
     *      so startJobIndex will between maxStartJobIndex and maxEndJobIndex;
     *      and endJobIndex will between startJobIndex and maxEndJobIndex;
     *
     *
     *       total_diff = hardest job of the day + total_diff of the rest days
     *       for part1, hardest job of the day we can get
     *       for part2, total_diff of jobs start from endJobIndex+1 we get from last day, which we stored in diff_map
     *       so we can get the total_diff for
     *          1. doing startJobIndex-endJobIndex in day before last day
     *          2. doing endJobIndex - lastJob in the last day
     *
     *       we loop the job range, and update `min total_diff start from ith job` in diff_map
     *
     *  --------------------------------------------------------------------------------------------
     *
     *    So we can have the logic for the question:
     *      1. as our base is the final day, we treat the question as we do the job backward,
     *          and build up the diff for the first day
     *      2. we have a diff_map, for ith element, it stores the min total_diff start from ith job
     *          and we will update it when each day pass
     *      3. each day we have a range of job we can do
     *      4. calculate the last day total_diff within the job range --> base case
     *      5. from day(d-1) to day(1)
     *          get the job range for that day
     *          for( startIndex : maxStartIndex to maxEndIndex)
     *              reset diff_map[startIndex]
     *              for(endIndex: startIndex to maxEndIndex)
     *                  total_diff = hardest job of the day + total_diff of the rest days
     *                  total_diff of the rest days = diff_map[endIndex+1]
     *
     *       one think need to pay attention, is that we have to go back day by day till day 1
     *       so, even if we have smaller value in diff_map from the previous day, we cannot use it.
     *       therefore, we have to reset the diff_map[startIndex] in each day
     *
     *       also before we move the startIndex from smallest to largest,
     *       the startIndex we need from previous day will be at least startIndex+1
     *       so we can reset the diff_map[startIndex]
     *
     *
     */
    public int minDifficulty5(int[] jobDifficulty, int d) {
        int totalJobs = jobDifficulty.length;
        if(totalJobs < d) return -1;

        int maxJobsOneDay = totalJobs -d +1;
        int[] map = new int[totalJobs];

        int maxDiff = Integer.MIN_VALUE;
        for(int k = totalJobs-1; k > totalJobs-1-maxJobsOneDay; k--){
            maxDiff = Math.max(maxDiff, jobDifficulty[k]);
            map[k] = maxDiff;
        }

        for(int day = d-1 ; day > 0; day--){

            int maxEndIndex = (totalJobs-1)-(d -day);
            int maxStartIndex = maxEndIndex- maxJobsOneDay +1;

            for(int startIndex = maxStartIndex; startIndex <= maxEndIndex; startIndex++){
                map[startIndex] = Integer.MAX_VALUE;
                int maxDiffOfTheDay = Integer.MIN_VALUE;
                for(int endIndex = startIndex; endIndex <= maxEndIndex; endIndex++){
                    maxDiffOfTheDay = Math.max(maxDiffOfTheDay, jobDifficulty[endIndex]);

                    int totalDiff = maxDiffOfTheDay + map[endIndex+1];

                    map[startIndex] = Math.min(map[startIndex], totalDiff);
                }
            }

        }

        return map[0];

    }

    @Test
    public void test(){

        int[] jobs = new int[]{6,5,7,2};
        minDifficulty4(jobs,3);

    }
}
