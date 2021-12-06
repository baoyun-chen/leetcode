package com.baoyun.leetcode.bfs_dfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/flood-fill/
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 *
 * You are also given three integers sr, sc, and newColor. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with newColor.
 *
 * Return the modified image after performing the flood fill.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 * Example 2:
 *
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
 * Output: [[2,2,2],[2,2,2]]
 *
 *
 * Constraints:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], newColor < 216
 * 0 <= sr < m
 * 0 <= sc < n
 */
public class No733 {

    //DFS: because for one node, we search to the end
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor){
        int m = image.length;
        int n = image[0].length;
        int origin = image[sr][sc];
        if (origin == newColor) return image;  // if already is newColor, don't need to print
        image[sr][sc] = newColor;
        //up
        if(sr - 1 >= 0 && image[sr-1][sc] == origin){
            image = floodFill(image, sr -1, sc, newColor);
        }

        //down
        if(sr + 1 < m && image[sr+1][sc] == origin){
            image = floodFill(image, sr +1, sc, newColor);
        }

        //left
        if(sc - 1 >= 0 && image[sr][sc-1] == origin){
            image = floodFill(image, sr, sc-1, newColor);
        }

        //right
        if(sc + 1 < n && image[sr][sc+1] == origin){
            image = floodFill(image, sr, sc+1, newColor);
        }
        return image;

    }

    @Test
    public void test(){
        int[][] image = new int[][]{new int[]{0,0,0},new int[]{0,1,1}};
        int sr= 1;
        int sc = 1;
        int newColor = 1;
        floodFill(image, sr, sc, newColor );
    }

    // BFS
    public int[][] floodFillBFS(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor){
            return image;
        }
        int[][] directions = new int[][]{new int[]{-1,0},new int[]{1,0},new int[]{0,-1},new int[]{0,+1}};
        Queue<int[]> queue = new LinkedList<>();
        int origin = image[sr][sc];
        image[sr][sc] = newColor;
        queue.offer(new int[]{sr,sc});
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            for(int[] direction: directions ){
                int y = direction[0]+node[0];
                int x = direction[1]+node[1];
                if(y>=0 && y< image.length && x>=0 && x<image[0].length && image[y][x] == origin){
                    image[y][x] =  newColor;
                    queue.offer(new int[]{y, x});
                }
            }
        }

        return image;

    }





}
