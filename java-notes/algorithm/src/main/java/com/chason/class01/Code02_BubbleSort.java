package com.chason.class01;

/**
 * 冒泡排序
 *
 * 和选择相反，相邻比较，然后将最大的放到最后
 */
public class Code02_BubbleSort {

    public static void bubbleSort(int[] arr) {

        if (arr == null || arr.length == 1) {
            return;
        }

        for (int p2 = arr.length-1; p2 > 0; p2--)
        {
            for (int p1=0; p1 < p2; p1++) {
                if (arr[p1] > arr[p1+1]) {
                    swap(arr, p1, p1+1);
                }
            }
        }
    }


    public static void swap(int[] arr, int p1, int p2) {
        arr[p1] = arr[p1] ^ arr[p2];
        arr[p2] = arr[p1] ^ arr[p2];
        arr[p1] = arr[p1] ^ arr[p2];
    }

}
