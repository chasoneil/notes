package com.chason.class02;

/**
 *  给定一个数组  arr[L...R]  使用递归的方式求这个数组的最大值
 *  实现：
 *  (1).将数组分成左右两半 [L...Mid] [Mid+1...R]
 *  (2).左边求最大值，右边也求最大值  (递归)
 *  (3).左右两边的两个最大值取大的就是整个数组的最大值
 */
public class Code07_ArrayMax {

    public static void getMaxFromArray(int[] arr) {
        getMaxFromArrayByRecursion(arr, 0, arr.length - 1);
    }

    // L 到 R 范围上求最大值
    public static int getMaxFromArrayByRecursion(int[] arr, int L, int R) {

        if (L == R) {  // L ~ R 区间只有一个数直接返回就行
            return arr[L];
        }

        // 递归的方式找区间之内最大的值
        int mid = L + (R - L) >> 1;
        int leftMax = getMaxFromArrayByRecursion(arr, L, mid);
        int rightMax = getMaxFromArrayByRecursion(arr, mid+1, R);

        return Math.max(leftMax, rightMax);
    }

}
