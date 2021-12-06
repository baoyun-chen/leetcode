package com.baoyun.leetcode.bfs_dfs;

/**
 * https://leetcode.com/problems/max-area-of-island/
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 */
public class No695 {

    private final int MARK = 2;
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int gridY = grid.length;
        int gridX = grid[0].length;
        for(int y = 0; y < gridY; y++){
            for (int x = 0; x < gridX; x++){
                maxArea = Math.max(getArea(grid, y, x), maxArea);
            }
        }

        return maxArea;

    }

    public int getArea(int[][] grid, int y, int x){
        int area = 0;
        if(grid[y][x] == 0 || grid[y][x] == MARK){
           return area;
        }
        // grid[y][x]=1
        area++;
        int gridY = grid.length;
        int gridX = grid[0].length;
        grid[y][x] = MARK;

        int[][] points = new int[][]{new int[]{-1,0},new int[]{1,0},new int[]{0,-1},new int[]{0,1}};
        for(int[] point : points){
            if(y+point[0] >= 0 && y+point[0]<gridY && x+point[1] >= 0 && x+point[1]<gridX){
                area += getArea(grid, y+point[0], x+point[1]);
            }
        }

        return area;
    }
}
