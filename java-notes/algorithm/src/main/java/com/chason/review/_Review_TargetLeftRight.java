package com.chason.review;

/*
    快速排序的第一个 partition
 */
public class _Review_TargetLeftRight {

    public static void main(String[] args) {

        int[] arr = {4, 2, 3, 5, 1, 3};
        leftAndRight(arr, 3);
        System.out.println(arr.toString());
    }

    public static void leftAndRight(int[] arr,int target) {

        if (arr == null || arr.length == 0) return;

        int L = -1;

        for (int i=0; i<arr.length; i++) {
            if (arr[i] <= target) {
                // exchange
                if (L + 1 < i) {
                    swap(arr, L+1, i);
                }
                L++;
            }
        }
    }

    public static void swap (int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
