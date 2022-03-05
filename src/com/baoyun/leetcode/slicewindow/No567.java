package com.baoyun.leetcode.slicewindow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.
 */
public class No567 {
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> characterMap = new HashMap<>();

        for(char character : s1.toCharArray()){
            if(!characterMap.containsKey(character)){
                characterMap.put(character,0);
            }
            characterMap.replace(character, characterMap.get(character)+1);
        }

        int left = 0;
        int right =0;
        char[] s2Array = s2.toCharArray();
        for(;right < s2Array.length; right++){
            char rightCharacter = s2Array[right];
            if(!characterMap.containsKey(rightCharacter)){
                characterMap.put(rightCharacter,0);
            }
            int number = characterMap.get(rightCharacter);
            characterMap.replace(rightCharacter, number-1);
            if(number -1 < 0 ){
                if(left < s2Array.length){
                    do{
                        characterMap.replace(s2Array[left], characterMap.get(s2Array[left]) +1);
                        left++;
                    }while (characterMap.get(s2Array[left-1]) != 0 );
                }

            }else{
                if(right-left+1 == s1.length()){
                    return true;
                }
            }
        }
        return false;

    }

    @Test
    public void test(){
       // String s1 ="hello";
        //String s2 ="ooolleoooleh";
        //checkInclusion(s1,s2);

        int n = 9&1;
        int j = 28&1;
    }
}
