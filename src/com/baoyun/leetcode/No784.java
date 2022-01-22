package com.baoyun.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * https://leetcode.com/problems/letter-case-permutation/
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 *
 * Return a list of all possible strings we could create. Return the output in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 *
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 12
 * s consists of lowercase English letters, uppercase English letters, and digits.
 */
public class No784 {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        result.add(s);
        int index = 0;
        int length = s.length();

        for(int i = index; i < length; i++){
            /*
            List<String> newAddResult = new ArrayList<>();
            for(String e : result){
                char thisChar = e.charAt(i);
                if(!Character.isDigit(thisChar)){
                    if(Character.isUpperCase(thisChar)){
                        thisChar = Character.toLowerCase(thisChar);
                    }else{
                        thisChar = Character.toUpperCase(thisChar);
                    }
                    if( i == length -1){
                        newAddResult.add(e.substring(0,i) + thisChar);
                    }else{
                        newAddResult.add(e.substring(0,i) + thisChar + e.substring(i+1));
                    }
                }
            }
            result.addAll(newAddResult);
             */
            int resultLength = result.size();
            for(int j = 0; j < resultLength; j++){
                char[] thisChars = result.get(j).toCharArray();
                if(!Character.isDigit(thisChars[i])){
                    if(Character.isUpperCase(thisChars[i])){
                        thisChars[i] = Character.toLowerCase(thisChars[i]);
                    }else{
                        thisChars[i] = Character.toUpperCase(thisChars[i]);
                    }
                    result.add(new String(thisChars));
                }
            }
        }
        return result;
    }

    public List<String> letterCasePermutation2(String s) {
        int length = s.length();
        List<String> result = new ArrayList<>();
        if(length == 1){
            if(Character.isDigit(s.charAt(0))){
                result.add(s);
            }else{
                result.add(s.toLowerCase());
                result.add(s.toUpperCase());
            }
            return result;
        }
        char first = s.charAt(0);
        result = letterCasePermutation2(s.substring(1));

            int resultLength = result.size();
            for(int i =0; i < resultLength; i++){
                if(!Character.isDigit(first)){
                    result.add(Character.toUpperCase(first) + result.get(i));
                }
                result.set(i, Character.toLowerCase(first) + result.get(i));
            }

        return result;

    }

    @Test
    public void test(){
        String s = "a1b2";
        letterCasePermutation(s);
    }
}
