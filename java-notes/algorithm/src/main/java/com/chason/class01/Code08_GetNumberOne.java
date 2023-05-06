package com.chason.class01;

/**
 * 给定一个整数 N
 * 返回这个整数 N 转化成二进制后 1 的个数
 *
 */
public class Code08_GetNumberOne {


    /**
     * 不断获取最右侧的1 ，然后将最右侧的1 踢掉
     * @param N
     */
    public static int getOne(int N) {

        int count = 0;

        while (N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
        }

        return count;
    }

}
