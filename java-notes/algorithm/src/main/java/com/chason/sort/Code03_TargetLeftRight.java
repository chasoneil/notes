package com.chason.sort;

/**
 * 快速排序
 * Partition Q1:
 */
public class Code03_TargetLeftRight {

    public static void main(String[] args) {

        int[] arr = {2,4,3,5,4,3,6,1};
        targetLeftRight(arr, 3);
    }


    public static void targetLeftRight(int[] arr, int target) {

        if (arr == null) {
            return;
        }

        int L = -1;

        for (int i=0; i<arr.length; i++) {
            if (arr[i] <= target) {
                if (L + 1 < i) {
                    swap(arr, L+1, i);
                }
                L++;
            }
        }

    }

    private static void swap (int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
