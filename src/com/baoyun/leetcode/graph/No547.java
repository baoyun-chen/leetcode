package com.baoyun.leetcode.graph;

import org.junit.Test;

/**
 *
 * https://leetcode.com/problems/number-of-provinces/
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class No547 {
    public int findCircleNum(int[][] isConnected) {
        int size = isConnected.length;
        unionSet set = new unionSet(size);
        for(int i = 0; i < size; i++){
            for(int j =i+1; j <size; j++){
                if(isConnected[i][j] == 1){
                    set.union(i,j);
                }
            }
        }
        int provinces = 0;
        int[] root = set.getRoot();
        for(int k = 0; k<size;k++){
            if(root[k] == k){
                provinces++;
            }
        }
        return provinces;
    }

    class unionSet{
        private int[] rank;
        private int[] root;  // each element is the parent node to the node i

        public unionSet(int size){
            this.rank = new int[size];
            this.root = new int[size];

            for(int i = 0; i<size; i++){
                this.rank[i] = 1;
                this.root[i] = i;
            }
        }

        public int find(int i){
            if(this.root[i] == i)
                return i;
            return this.root[i] = find(this.root[i]);
        }

        public void union(int i, int j){
            int rooti = find(i);
            int rootj = find(j);
            if(rooti == rootj)
                return;
            if(rank[rooti] < rank[rootj]){
                this.root[rooti] = rootj;
            }else if(rank[rooti] > rank[rootj]){
                this.root[rootj] = rooti;
            }else{
                this.root[rooti] = rootj;
                this.rank[rootj]++;
            }
        }

        public int[] getRoot() {
            return root;
        }
    }

    @Test
    public void test(){
        int[][] isConnected = {
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},
                {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,0,1,0,0,0,1,0,0,0,1,0,0,0},
                {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,1,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
                {0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}};

        int[][] isconnected1 = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};

        findCircleNum2(isconnected1);

    }

    public int findCircleNum2(int[][] isConnected) {
        int size = isConnected.length;
        int provinces = 0;
        for(int i = 0; i<size;i++){
            provinces = provinces + checkConnection(isConnected,i);
        }
        return provinces;
    }

    public int checkConnection(int[][] isConnected, int i){
        int size = isConnected.length;
        for(int j = 0; j < size; j++ ){
            if(i==j){
                continue;
            }
            if(isConnected[i][j] == -1){
                return 0;
            }
            if(isConnected[i][j] == 1){ // need to mark all node that is connected to i
                checkConnection(isConnected, j);
                isConnected[i][j] = -1;
                isConnected[j][i] = -1;// use -1 to mark is checked
            }
        }
        return 1;
    }
}
