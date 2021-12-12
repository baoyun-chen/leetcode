package com.baoyun.leetcode.bfs_dfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * https://leetcode.com/problems/rotting-oranges/
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 *
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 */
public class No994 {

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int orange = 0;
        int rottedOrange = 0;
        int min = 0;
        Queue<int[]> visitedRottedOrange = new LinkedList<>();

        int[] dir = new int[]{0,1,0,-1,0};
        for(int y = 0; y<m; y++){
            for(int x = 0; x<n; x++){
                switch (grid[y][x]){
                    case 2:
                        rottedOrange++;
                        visitedRottedOrange.offer(new int[]{y,x});
                    case 1:
                        orange++;
                        break;
                }
            }
        }
        // point: if all the orange are rotted in min 0, return 0
        if(rottedOrange == orange){
            return 0;
        }
        if(rottedOrange == 0){
            return -1;
        }
        do{

            Queue<int[]> newRootedOrange = new LinkedList<>();
            while(!visitedRottedOrange.isEmpty()){
                int[] oldRottedOrange = visitedRottedOrange.poll();
                for(int index = 0; index < dir.length -1; index++){
                    int ny = dir[index] + oldRottedOrange[0];
                    int nx = dir[index+1] + oldRottedOrange[1];
                    if(ny >=0 && ny <m && nx >=0 && nx <n && grid[ny][nx] == 1){
                        grid[ny][nx] = 2;
                        newRootedOrange.offer(new int[]{ny, nx});
                        rottedOrange++;
                    }
                }
            }
            visitedRottedOrange = newRootedOrange;
            min++;

            // if no new rotted orange, we can break
            // if all the orange are rotted, we can break
        }while((!visitedRottedOrange.isEmpty()) && (rottedOrange < orange));


        if(rottedOrange == orange){
            return min;
        }

        // when breaks, if not the oranges are rotted, that is impossible to rot all the oranges
        return -1;

    }

    @Test
    public void test(){
        int[][] mat = new int[][]{new int[]{0,2}};
        orangesRotting(mat);
    }
}
