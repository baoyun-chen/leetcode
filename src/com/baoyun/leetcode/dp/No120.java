package com.baoyun.leetcode.dp;

import java.util.List;

/**
 * https://leetcode.com/problems/triangle/
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 *
 *
 * Example 1:
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 *
 * Input: triangle = [[-10]]
 * Output: -10
 *
 *
 * Constraints:
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 *
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */
public class No120 {


    /**
     * From top to bottom,
     *
     * For each position, we record the min path to go the that position. save in triangle itself
     *
     * r = rowNumber, i = index
     * MinPath of (r,i) = MinPath(triangle(r-1,i), triangle(r-1,i-1)) + triangle(r,i)
     * need to consider if row r-1 has index i or index i-1
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowNumber = 0;
        int min = triangle.get(0).get(0);
        for(List<Integer> row : triangle ){
            if(rowNumber >0){
                int index = 0;
                min = Integer.MAX_VALUE;
                for(Integer number : row){
                    int numberOnIndex = Integer.MAX_VALUE;
                    int numberOnIndexMinusOne = Integer.MAX_VALUE;
                    int newSum;
                    if(triangle.get(rowNumber-1).size() >= (index +1)) {
                        numberOnIndex = triangle.get(rowNumber-1).get(index);
                    }
                    if(index -1 >= 0){
                        numberOnIndexMinusOne = triangle.get(rowNumber-1).get(index-1);
                    }
                    newSum = Math.min(numberOnIndex, numberOnIndexMinusOne)+number;
                    row.set(index, newSum);
                    min = Math.min(min,newSum);
                    index++;
                }
            }
            rowNumber++;
        }
        return min;
    }


    /**
     * Similar to minimumTotal1 but from bottom to top
     *
     * From the second last layer, we record the minPath in the triangle itself
     *
     * r = rowNumber
     * i = index
     * MinPath(r,i) = Min( MinPath(r+1,i), MinPath(r+1,i+1)) + triangle(r,i)
     *
     * The first row would be the total min path
     *
     *
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int rowSize = triangle.size();

        for(int r = rowSize-2; r >= 0; r--){
            for(int i = 0; i < triangle.get(r).size(); i++){
                int min = Math.min(triangle.get(r+1).get(i), triangle.get(r+1).get(i+1));
                triangle.get(r).set(i, min + triangle.get(r).get(i));
            }
        }
        return triangle.get(0).get(0);
    }

}
