package com.chason.dichotomy;

/**
 *  在有序数组中 找 >= 某个数最左侧的位置
 *
 *  1，2，2，2，3，3，3，3，3，4，4，4    >= 2 最左侧的位置 1
 *
 */
public class Code02_BSNearestLeft {

    public static int getNearestLeft (int[] arr, int target) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;

        if (target < arr[L] || target > arr[R]) {
            return -1;
        }

        int mid = 0;
        while (L < R) {

            mid = L + (R - L) >> 1;

            //  todo


        }
        return 0;

    }


}
