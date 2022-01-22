package com.baoyun.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/combinations/
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class No77 {


    // 这个的思考是，在1-n 直接选k个值
    // 就可以分为两种情况，一个是包含n 的， 一个是不包含n 的
    // 包含n的 就是 n 占一个位置，其他k-1个位置的是 1-（n-1)，
    // 相当于先算出 combine(n-1, k-1) 再在每一个element 里面加上n
    // 不包含n的，就是单纯的 combine(n-1,k) 了
    //
    public List<List<Integer>> combine( int n, int k){
        int start = 1;
        List<List<Integer>> result = new ArrayList<>(new ArrayList<>());
        if(k == start){
            for(int i = start; i<=n;i++){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                result.add(list);
            }
            return result;
        }

        if( n == k ){
            result.add(IntStream.rangeClosed(start,n).boxed().collect(Collectors.toList()));
            return result;
        }

        List<List<Integer>> partResult = combine(n-1,k-1);

        partResult.forEach(e->e.add(n));
       /*  这种耗时长
        Iterator<List<Integer>> iterator = partResult.iterator();
        while(iterator.hasNext()){
            iterator.next().add(n);
        }
        */
        // 这种耗时长
       // result = Stream.concat(partResult.stream(), combine(n-1,k).stream()).collect(Collectors.toList());
        result = combine(n-1,k);
        result.addAll(partResult);

        return result;
    }

    @Test
    public void test(){
        combine(4,2);
    }

}
