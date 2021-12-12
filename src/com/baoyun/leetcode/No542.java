package com.baoyun.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * Example 2:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 */
public class No542 {

    /**
     * DP way
     * point: DP only work with the processed/fixed previous value,
     * when not all value are processed/fixed,
     * we can consider use some processed/fixed value first and process multiple times
     *
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int longest = m + n;

        for(int y = 0; y < m; y++){
            for(int x =0; x < n; x++){
                if(mat[y][x] != 0){
                    mat[y][x] = longest;
                    if(y-1 >= 0){
                        mat[y][x] = Math.min(mat[y][x],mat[y-1][x]+1);
                    }
                    if(x-1 >= 0){
                        mat[y][x] = Math.min(mat[y][x],mat[y][x-1]+1);
                    }
                }
            }
        }

        for(int y = m-1 ; y >= 0; y--){
            for(int x = n-1; x >= 0; x--){
                if(mat[y][x] != 0){
                    if(y+1 < m){
                        mat[y][x] = Math.min(mat[y][x],mat[y+1][x]+1);
                    }
                    if(x+1 < n){
                        mat[y][x] = Math.min(mat[y][x],mat[y][x+1]+1);
                    }
                }
            }
        }
        return mat;

    }


    /**
     *
     *  BFS： from the visited node, mark all the neighbour, add the new visited node into queue
     *  we add 0 to the visited node queue, then start process
     *  the neighbour of 0 will become 0+1, if it is unvisited, that would be the shortest path
     *  because we put all 0 node into the queue first, then put others,
     *  we will process the 0 neighbour first, then the neighbour of other numbers
     *  we can make sure the neighbour = visited node +1 is the shortest
     *
     */
    public int[][] updateMatrix2(int[][] mat) {
        int[] dir = new int[]{0,1,0,-1,0};
        Queue<int[]> processed = new LinkedList<>();

        int m = mat.length;
        int n = mat[0].length;

        for(int y = 0; y < m; y++){
            for(int x = 0; x<n; x++){
                if(mat[y][x] == 0){
                    processed.offer(new int[]{y,x});
                }else{
                    mat[y][x] = -1;
                }
            }
        }

        while(!processed.isEmpty()){
            int[] node = processed.poll();
            for(int index = 0; index <dir.length-1; index++){
                int ny = dir[index] + node[0];
                int nx = dir[index+1] + node[1];
                if(ny >= 0 && ny < m && nx >= 0 && nx < n && mat[ny][nx] == -1){
                    mat[ny][nx] = mat[node[0]][node[1]]+1;
                    processed.offer(new int[]{ny,nx});
                }
            }
        }

        return mat;

    }

    @Test
    public void test(){
        int[][] mat = new int[][]{new int[]{0,0,0},new int[]{0,1,0},new int[]{1,1,1}};
        updateMatrix2(mat);
    }





}
