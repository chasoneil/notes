package com.chason.sort.low;

/**
 * 选择排序
 *
 * 选择排序的思想就是比较并交换，将最小的值放到前面
 */
public class Code01_SelectSort {

    public static void selectSort(int[] arr) {

        if (arr == null || arr.length == 1) {
            return;
        }

        for (int p1=0; p1<arr.length-1; p1++) {
            for (int p2=p1+1; p2<arr.length; p2++) {
                if (arr[p1] > arr[p2]) {
                    swap(arr, p1, p2);
                }
            }
        }
    }

    public static void swap(int[] arr, int p1, int p2) {
        int tmp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = tmp;
    }


}
