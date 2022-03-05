package com.baoyun.leetcode.bit;

/**
 *
 * https://leetcode.com/problems/reverse-bits/
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Note:
 *
 * Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 *
 *
 * Example 1:
 *
 * Input: n = 00000010100101000001111010011100
 * Output:    964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * Example 2:
 *
 * Input: n = 11111111111111111111111111111101
 * Output:   3221225471 (10111111111111111111111111111111)
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 *
 *
 * Constraints:
 *
 * The input must be a binary string of length 32
 *
 *
 * Follow up: If this function is called many times, how would you optimize it?
 */
public class No190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for(int i = 0; i<32;i++){
            result = result<<1; // move 1 bit to the left to add the last bit
            result += (n&1);   // get the last bit and add to the last bit in result
            n=n>>>1; //remove the last bit
        }
        return result;
    }


    //  12345678 -> 56781234 -> 78563412 -> 81544321
    // 0xff00ff00     every position represent one 4 bits, represent from 0000 to 1111 (0-9-a-f)
    public int reverseBits2(int n){
        // as an int is 32 bit, first move right 16 bit and move left 16 bit
        // get 00001234   and 56780000
        // with "|", we revert the first 16 bits and the last 16 bits -> 56781234
        n = (n>>>16) | (n<<16);
        //  n & 0xff00ff00  -> we get 56001200, we make the 9-16, 25-32 bit 0
        //  move right 8 bit, get 00560012
        //  n & 0x00ff00ff -> we get00780034, we make the 1-8, 17-24 bit 0
        //  move left 8 bit, get 78003400
        //  with "|" get 78563412  -- and so on
        n = ((n & 0xff00ff00)>>>8) | ((n & 0x00ff00ff )<<8);
        n = ((n & 0xf0f0f0f0)>>>4) | ((n & 0x0f0f0f0f )<<4);
        n = ((n & 0xcccccccc)>>>2) | ((n & 0x33333333 )<<2);
        n = ((n & 0xaaaaaaaa)>>>1) | ((n & 0x55555555 )<<1);
        return n;
    }
}
