package com.chason.sort;

/**
 * 快速排序
 * 也是通过递归思想，将荷兰问题和递归完美结合
 */
public class Code05_FastSort {

    public static void fastSort (int[] arr) {
        process2(arr, 0, arr.length);
    }

    public static void process2 (int[] arr, int L, int R) {

        if (L >= R) {
            return;
        }
        // 先用荷兰国旗问题将数组分成左右两边
        int[] area = Code04_DutchFlag.fastSortPartition(arr, L, R);
        process2(arr, L , area[0] - 1);
        process2(arr, area[1] + 1, R);
    }

}
